package org.spdgroup.bigbitebackend.utils.gcloud;

import com.google.cloud.spring.core.GcpProjectIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GcpConfig {

    @Bean
    public GcpProjectIdProvider gcpProjectIdProvider() {
        return () -> "big-bite-436521";
    }
}
