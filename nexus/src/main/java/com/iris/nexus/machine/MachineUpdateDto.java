package com.iris.nexus.machine;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.util.UUID;

@Value
public class MachineUpdateDto {

    @NotNull
    UUID id;

    String name;
    String host;

    @Min(1)
    @Max(65535)
    Integer sshPort;

    String sshUser;
    String sshKeyEnc;
    String secretKeyId;
    String mgmtIface;
    MachineStatus status;
}
