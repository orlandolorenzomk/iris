package com.iris.nexus.space.network.internal;

import com.iris.nexus.space.internal.Space;
import com.iris.nexus.space.network.SpaceNetworkStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "space_networks")
public class SpaceNetwork {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id", nullable = false)
    private Space space;

    @NotNull
    @Column(name = "vnet_id", nullable = false)
    private String vnetId;

    @NotNull
    @Column(name = "subnet", nullable = false)
    private String subnet;

    @Column(name = "vni")
    private Integer vni;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SpaceNetworkStatus status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;
}
