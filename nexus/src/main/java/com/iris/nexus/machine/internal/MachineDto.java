package com.iris.nexus.machine.internal;

import com.iris.nexus.machine.MachineStatus;
import lombok.Value;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.UUID;

@Value
public class MachineDto implements Serializable {
    UUID id;
    String name;
    String host;
    Integer sshPort;
    String sshUser;
    String mgmtIface;
    Boolean isPrimary;
    MachineStatus status;
    OffsetDateTime createdAt;
}
