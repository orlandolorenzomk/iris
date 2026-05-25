CREATE TABLE machine_networks (
    id                UUID    NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    machine_id        UUID    NOT NULL REFERENCES machines(id),
    space_network_id  UUID    NOT NULL REFERENCES space_networks(id),
    status            TEXT    NOT NULL DEFAULT 'pending_apply',
    applied_at        TIMESTAMPTZ
);
