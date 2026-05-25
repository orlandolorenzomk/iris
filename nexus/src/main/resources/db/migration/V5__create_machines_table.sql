CREATE TABLE machines (
    id             UUID    NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    name           TEXT    NOT NULL UNIQUE,
    host           TEXT    NOT NULL,
    ssh_port       INT     NOT NULL DEFAULT 22,
    ssh_user       TEXT    NOT NULL,
    ssh_key_enc    TEXT    NOT NULL,
    secret_key_id  TEXT    NOT NULL,
    mgmt_iface     TEXT    NOT NULL,
    is_primary     BOOLEAN NOT NULL DEFAULT FALSE,
    status         TEXT    NOT NULL DEFAULT 'bootstrapping',
    created_at     TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
