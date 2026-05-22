# Iris Project Overview

**Iris** is a lightweight, super-fast cloud platform built directly on Proxmox. It uses almost zero resources when idle because it creates infrastructure only on demand.

## 1. User Spaces (Multi-Tenancy)

Every user gets an isolated environment called a **User Space**.

* **Resource Caps:** Users are assigned strict limits on CPU, RAM, and storage.
* **Network Isolation:** A user's applications run inside their own virtual network, meaning they cannot see or access another user's databases or files.

---

## 2. Deployment Choices

Inside their User Space, a user can deploy two types of workloads:

### A. Custom Apps (Bring Your Own Code)

* **How it works:** The user runs `docker push` to send their own custom Docker image to **Iris Aurora** (the Python registry).
* **Result:** **Iris Prism** (the C engine) catches the image and spins it up inside a Virtual Machine in the user's space.

### B. Built-In Iris Services (On-Demand Catalog)

Instead of writing code, a user can instantly provision pre-configured, ad-hoc cloud services with a single click or command:

* **S3-Clone (Object Storage):** Launches a dedicated, private *MinIO* container for storing user files.
* **Cognito-Clone (Identity/Auth):** Launches a private *Authelia/Keycloak* instance for user login management.
* **Database Service:** Launches a standalone, lightning-fast **LXC container** running *PostgreSQL* or *Redis*.

---

## 3. Core Component Breakdown

* **Iris Prism (C Backend):** Runs with root privileges on the main Proxmox host. It listens for commands, verifies user tokens (JWTs) in microseconds, and instantly runs native system commands (`qm` or `pct`) to build the VMs, LXCs, or Docker apps.
* **Iris Aurora (Python Registry):** Manages all Docker image layers, handles user uploads, and keeps track of image tags inside a central **PostgreSQL database**.
