ALTER TABLE space_networks
    DROP CONSTRAINT space_networks_space_id_fkey,
    ADD CONSTRAINT space_networks_space_id_fkey
        FOREIGN KEY (space_id) REFERENCES spaces(id) ON DELETE CASCADE;
