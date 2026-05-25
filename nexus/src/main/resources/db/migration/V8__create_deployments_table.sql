CREATE TABLE deployments (
    id            UUID    NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    space_id      UUID    NOT NULL REFERENCES spaces(id),
    machine_id    UUID    NOT NULL REFERENCES machines(id),
    image_ref     TEXT    NOT NULL,
    vm_id         TEXT,
    container_id  TEXT,
    status        TEXT    NOT NULL DEFAULT 'pending',
    created_at    TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
