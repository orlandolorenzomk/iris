package com.iris.nexus.machine.network.internal;

import com.iris.nexus.machine.internal.Machine;
import com.iris.nexus.machine.network.MachineNetworkStatus;
import com.iris.nexus.space.network.internal.SpaceNetwork;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "machine_networks")
public class MachineNetwork {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id", nullable = false)
    private Machine machine;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_network_id", nullable = false)
    private SpaceNetwork spaceNetwork;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MachineNetworkStatus status;

    @Column(name = "applied_at")
    private OffsetDateTime appliedAt;
}
