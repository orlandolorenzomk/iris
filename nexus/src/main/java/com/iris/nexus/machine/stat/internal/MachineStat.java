package com.iris.nexus.machine.stat.internal;

import com.iris.nexus.machine.internal.Machine;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "machine_stats")
public class MachineStat {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id", nullable = false, unique = true)
    private Machine machine;

    @Column(name = "cpu_free")
    private Double cpuFree;

    @Column(name = "cpu_total")
    private Double cpuTotal;

    @Column(name = "ram_free")
    private Long ramFree;

    @Column(name = "ram_total")
    private Long ramTotal;

    @Column(name = "disk_free")
    private Long diskFree;

    @Column(name = "disk_total")
    private Long diskTotal;

    @Column(name = "active_deployments")
    private Integer activeDeployments;

    @Column(name = "last_heartbeat")
    private OffsetDateTime lastHeartbeat;
}
