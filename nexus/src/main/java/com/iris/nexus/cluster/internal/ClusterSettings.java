package com.iris.nexus.cluster.internal;

import com.iris.nexus.cluster.SdnZoneType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "cluster_settings")
public class ClusterSettings {

    @Id
    @Column(name = "lock", nullable = false)
    private Boolean lock = true;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "sdn_zone_type", nullable = false)
    private SdnZoneType sdnZoneType;

    @NotBlank
    @Column(name = "sdn_zone_name", nullable = false)
    private String sdnZoneName;

    @NotBlank
    @Column(name = "nats_url", nullable = false)
    private String natsUrl;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;
}
