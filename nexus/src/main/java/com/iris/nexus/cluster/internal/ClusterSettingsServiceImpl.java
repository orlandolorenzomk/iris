package com.iris.nexus.cluster.internal;

import com.iris.nexus.cluster.ClusterSettingsDto;
import com.iris.nexus.cluster.ClusterSettingsService;
import com.iris.nexus.cluster.ClusterSettingsUpdateDto;
import com.iris.nexus.shared.exception.AppException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClusterSettingsServiceImpl implements ClusterSettingsService {

    private final ClusterSettingsRepository clusterSettingsRepository;
    private final ClusterSettingsMapper clusterSettingsMapper;

    private volatile ClusterSettingsDto cached;

    @Transactional(readOnly = true)
    public void loadCache() {
        ClusterSettings settings = clusterSettingsRepository.findById(true)
                .orElseThrow(() -> new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Cluster settings not found"));
        cached = clusterSettingsMapper.toDto(settings);
        log.info("Cluster settings loaded into cache");
    }

    @Override
    public ClusterSettingsDto getSettings() {
        return cached;
    }

    @Override
    @Transactional
    public ClusterSettingsDto update(ClusterSettingsUpdateDto dto) {
        ClusterSettings settings = clusterSettingsRepository.findById(true)
                .orElseThrow(() -> new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Cluster settings not found"));
        clusterSettingsMapper.partialUpdate(dto, settings);
        cached = clusterSettingsMapper.toDto(clusterSettingsRepository.save(settings));
        log.info("Cluster settings updated and cache refreshed");
        return cached;
    }
}
