package com.iris.nexus.space.internal;

import com.iris.nexus.auth.user.internal.User;
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
@Table(name = "spaces")
public class Space {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "cpu_limit", nullable = false)
    private Integer cpuLimit;

    @NotNull
    @Column(name = "ram_limit", nullable = false)
    private Integer ramLimit;

    @NotNull
    @Column(name = "storage_limit", nullable = false)
    private Long storageLimit;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;
}
