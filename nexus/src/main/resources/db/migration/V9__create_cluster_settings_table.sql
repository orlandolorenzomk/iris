CREATE TABLE cluster_settings
(
    lock           BOOLEAN PRIMARY KEY DEFAULT TRUE,
    sdn_zone_type  TEXT        NOT NULL,
    sdn_zone_name  TEXT        NOT NULL,
    nats_url       TEXT        NOT NULL,
    updated_at     TIMESTAMPTZ,
    CONSTRAINT single_row CHECK (lock)
);
