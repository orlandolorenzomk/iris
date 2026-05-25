package com.iris.nexus.auth;

import com.iris.nexus.auth.user.UserCreateDto;
import com.iris.nexus.auth.user.UserService;
import com.iris.nexus.auth.user.internal.UserRepository;
import com.iris.nexus.cluster.SdnZoneType;
import com.iris.nexus.cluster.internal.ClusterSettings;
import com.iris.nexus.cluster.internal.ClusterSettingsRepository;
import com.iris.nexus.cluster.internal.ClusterSettingsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationRunner {

    @Value("${app.admin.default-email}")
    private String defaultEmail;

    @Value("${app.admin.default-password}")
    private String defaultPassword;

    @Value("${app.cluster.sdn-zone-type}")
    private String sdnZoneType;

    @Value("${app.cluster.sdn-zone-name}")
    private String sdnZoneName;

    @Value("${app.cluster.nats-url}")
    private String natsUrl;

    private final UserRepository userRepository;
    private final UserService userService;
    private final ClusterSettingsRepository clusterSettingsRepository;
    private final ClusterSettingsServiceImpl clusterSettingsService;

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() == 0) {
            userService.create(new UserCreateDto(defaultEmail, defaultPassword));
            log.info("Default owner created: {}", defaultEmail);
        }

        if (clusterSettingsRepository.count() == 0) {
            ClusterSettings settings = new ClusterSettings();
            settings.setSdnZoneType(SdnZoneType.valueOf(sdnZoneType));
            settings.setSdnZoneName(sdnZoneName);
            settings.setNatsUrl(natsUrl);
            clusterSettingsRepository.save(settings);
            log.info("Cluster settings seeded from environment");
        }

        clusterSettingsService.loadCache();
    }
}
