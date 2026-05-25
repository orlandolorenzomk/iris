CREATE TABLE spaces (
    id            UUID    NOT NULL DEFAULT gen_random_uuid() PRIMARY KEY,
    user_id       UUID    NOT NULL REFERENCES users(id),
    name          TEXT    NOT NULL,
    cpu_limit     INT     NOT NULL,
    ram_limit     INT     NOT NULL,
    storage_limit BIGINT  NOT NULL,
    created_at    TIMESTAMPTZ NOT NULL DEFAULT NOW()
);
