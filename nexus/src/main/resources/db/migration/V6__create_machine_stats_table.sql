CREATE TABLE machine_stats (
    id                  UUID    NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    machine_id          UUID    NOT NULL UNIQUE REFERENCES machines(id),
    cpu_free            DOUBLE PRECISION,
    cpu_total           DOUBLE PRECISION,
    ram_free            BIGINT,
    ram_total           BIGINT,
    disk_free           BIGINT,
    disk_total          BIGINT,
    active_deployments  INT     NOT NULL DEFAULT 0,
    last_heartbeat      TIMESTAMPTZ
);
