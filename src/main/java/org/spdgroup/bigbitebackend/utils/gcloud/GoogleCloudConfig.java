package org.spdgroup.bigbitebackend.utils.gcloud;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Configuration
public class GoogleCloudConfig {

    @Bean
    public Storage storage() throws IOException {
        String credentialsJson = System.getenv("GCP_CREDENTIALS");
        if (credentialsJson == null) {
            throw new IllegalStateException("GCP_CREDENTIALS environment variable is not set");
        }
        InputStream credentialsStream = new ByteArrayInputStream(credentialsJson.getBytes());
        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);
        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }
}