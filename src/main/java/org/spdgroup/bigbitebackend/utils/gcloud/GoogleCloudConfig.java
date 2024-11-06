package org.spdgroup.bigbitebackend.utils.gcloud;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class GoogleCloudConfig {

    @Value("${GCP_CREDENTIALS}")
    private String gcpCredentials;

    @Bean
    public Storage storage() throws IOException {
        try (InputStream credentialsStream = new ByteArrayInputStream(gcpCredentials.getBytes())) {
            GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);
            return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        }
    }
}