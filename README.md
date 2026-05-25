# Iris

Iris is a lightweight, self-hosted cloud platform built directly on Proxmox. It creates infrastructure only on demand — using near-zero resources when idle.

---

## Components

| Component | Stack | Status | Role |
|---|---|---|---|
| **Iris Nexus** | Spring Boot 3 + Java 21 | In progress | Control plane — single public entry point |
| **Iris Panel** | Angular | Planned | Web dashboard |
| **Iris CLI** | — | After Nexus stable | Terminal client |
| **Iris Prism** | Zig | Planned | Per-node infrastructure daemon (VMs, LXCs, networks) |
| **Iris Orbit** | Zig | Planned | Per-node container orchestration daemon |
| **Iris Aurora** | Python | Planned | OCI-compliant Docker image registry |

---

## Architecture

Nexus is the only component exposed to the outside world. All traffic from Panel and CLI flows through it, and all commands to the internal engines originate from it.

```
Iris Panel  ──┐
Iris CLI    ──┤  REST / WS  ┌─────────────┐   NATS JetStream   ┌─────────────┐
              └────────────▶│ Iris Nexus  │──────────────────▶  │ Iris Prism  │  (per node)
Docker CLI  ──────────────▶ │             │──────────────────▶  │ Iris Orbit  │  (per node)
              HTTP OCI      └─────────────┘                     └─────────────┘
                                   │
                             Iris Aurora
                          (NATS + HTTP OCI)
```

Each Proxmox node runs one Prism instance and one Orbit instance. Nexus publishes commands to NATS and each daemon subscribes only to subjects scoped to its own node ID.

---

## How It Works

### User Spaces

Every user gets an isolated environment called a **User Space** — one virtual network, one quota envelope. Inside a space a user can run many services (web app, worker, PostgreSQL, Redis), each in its own VM or LXC.

Resource caps (CPU, RAM, storage) are enforced by Nexus before any deployment request reaches Prism.

### Network Isolation

Each User Space maps 1-to-1 to a Proxmox SDN VNet. VMs in different spaces share no L2 network, no broadcast domain, and no IP route. Isolation is enforced at three independent layers:

| Layer | Technology | Enforces |
|---|---|---|
| 1 | Proxmox SDN VNet | L2 isolation — no bridge connectivity between spaces |
| 2 | Proxmox Firewall | L3/L4 DROP rules between VNet subnets |
| 3 | nftables (host) | Fallback — blocks cross-bridge forwarding on the Proxmox host |

On a single node, Iris uses a **Simple** SDN zone (Linux bridges). On a multi-node cluster, it uses **VXLAN** so a User Space can span multiple nodes transparently over UDP port 4789.

### Deployments

Users can deploy two types of workloads inside their space:

**Custom apps** — push any Docker image to Iris Aurora with `docker push`, then deploy it from Panel or CLI. Prism provisions a VM; Orbit pulls the image inside the VM and manages the container lifecycle (restart, health checks, scaling).

**Built-in services** — provision pre-configured services with a single click:
- PostgreSQL / Redis — lightweight LXC containers
- MinIO — S3-compatible object storage
- Authelia / Keycloak — identity and auth

### Node Scheduling

When placing a workload, Nexus scores available nodes by free capacity:

```
score = (cpu_free / cpu_total × 0.4) + (ram_free / ram_total × 0.4) + (disk_free / disk_total × 0.2)
```

Nodes that cannot satisfy the workload's requirements are filtered out first. Tiebreaker is fewest active deployments. If a node misses heartbeats for 30 seconds it is marked unavailable and excluded from scheduling.

---

## Repository Structure

```
iris/
├── nexus/          # Spring Boot control plane API
│   ├── src/
│   ├── docker-compose.yml
│   ├── .env.example
│   └── pom.xml
└── docs/           # Architecture documentation (AsciiDoc)
    ├── nexus-architecture.adoc
    ├── prism-architecture.adoc
    ├── aurora-architecture.adoc
    ├── orbit-architecture.adoc
    └── network-isolation.adoc
```

---

## Nexus — Quick Start

**Requirements:** Java 21, Maven, Docker

```bash
cd nexus
cp .env.example .env          # fill in secrets
docker compose up -d          # start PostgreSQL on port 5434
./mvnw spring-boot:run        # start Nexus on port 8081
```

On first startup Nexus seeds the default OWNER account and cluster settings from `.env`, then runs Flyway migrations (V1–V9).

### Environment Variables

| Variable | Description |
|---|---|
| `SERVER_PORT` | Port Nexus listens on |
| `JWT_SECRET` | Base64-encoded 256-bit HS256 signing key |
| `JWT_EXPIRATION` | Token TTL in milliseconds |
| `DB_*` / `POSTGRES_*` | PostgreSQL connection + Docker init |
| `ADMIN_DEFAULT_EMAIL` | Email for the seeded OWNER account |
| `ADMIN_DEFAULT_PASSWORD` | Password for the seeded OWNER account |
| `SDN_ZONE_TYPE` | `SIMPLE` or `VXLAN` |
| `SDN_ZONE_NAME` | Proxmox SDN zone name (e.g. `iris`) |
| `NATS_URL` | NATS JetStream connection URL |

### API

Import `nexus/nexus.postman_collection.json` into Postman. The Login request automatically saves the JWT to a global variable used by all other requests.

Endpoints: `/auth`, `/auth/users`, `/spaces`, `/space-networks`, `/machines`, `/cluster-settings`

---

## Messaging

All communication between Nexus and the internal engines uses **NATS JetStream** for at-least-once delivery. Subject convention:

```
iris.{component}.{node_id}.{action}    # commands  (Nexus → engine)
iris.heartbeat.{node_id}               # heartbeat (Prism → Nexus)
iris.events.{domain}                   # events    (engine → Nexus)
```

Docker push/pull between the Docker CLI and Aurora uses HTTP OCI (the Docker protocol requirement). Everything else — image deploy triggers, deploy events, heartbeats — goes through NATS.

---

## Security

- User JWTs never leave Nexus — NATS messages carry a separate service-level credential
- SSH keys for machine registration are AES-256 encrypted at rest and never exposed through the API
- Prism runs as root on the Proxmox host only and is never exposed to the network directly
- Aurora registry tokens are short-lived and scoped to a specific repository and operation
- VXLAN inter-node traffic is unencrypted — the node management network should be a dedicated, isolated interface in production
