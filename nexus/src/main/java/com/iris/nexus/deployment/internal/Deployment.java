package com.iris.nexus.deployment.internal;

import com.iris.nexus.deployment.DeploymentStatus;
import com.iris.nexus.machine.internal.Machine;
import com.iris.nexus.space.internal.Space;
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
@Table(name = "deployments")
public class Deployment {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "space_id", nullable = false)
    private Space space;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "machine_id", nullable = false)
    private Machine machine;

    @NotNull
    @Column(name = "image_ref", nullable = false)
    private String imageRef;

    @Column(name = "vm_id")
    private String vmId;

    @Column(name = "container_id")
    private String containerId;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DeploymentStatus status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;
}
