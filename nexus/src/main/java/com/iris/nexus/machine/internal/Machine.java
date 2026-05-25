package com.iris.nexus.machine.internal;

import com.iris.nexus.machine.MachineStatus;
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
@Table(name = "machines")
public class Machine {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @NotNull
    @Column(name = "host", nullable = false)
    private String host;

    @NotNull
    @Column(name = "ssh_port", nullable = false)
    private Integer sshPort;

    @NotNull
    @Column(name = "ssh_user", nullable = false)
    private String sshUser;

    @NotNull
    @Column(name = "ssh_key_enc", nullable = false)
    private String sshKeyEnc;

    @NotNull
    @Column(name = "secret_key_id", nullable = false)
    private String secretKeyId;

    @NotNull
    @Column(name = "mgmt_iface", nullable = false)
    private String mgmtIface;

    @NotNull
    @Column(name = "is_primary", nullable = false)
    private Boolean isPrimary;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MachineStatus status;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;
}
