package com.iris.nexus.cluster;

public interface ClusterSettingsService {

    ClusterSettingsDto getSettings();

    ClusterSettingsDto update(ClusterSettingsUpdateDto dto);
}
