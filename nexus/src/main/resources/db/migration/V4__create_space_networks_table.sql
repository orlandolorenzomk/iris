CREATE TABLE space_networks (
    id         UUID NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    space_id   UUID NOT NULL REFERENCES spaces(id),
    vnet_id    TEXT NOT NULL,
    subnet     TEXT NOT NULL,
    vni        INT,
    status     TEXT NOT NULL DEFAULT 'pending_apply',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
