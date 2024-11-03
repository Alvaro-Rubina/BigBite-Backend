package org.spdgroup.bigbitebackend.utils.gcloud;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.core.io.Resource;

@Configuration
public class GoogleCloudConfig {

    @Value("classpath:gcp-credentials-bigbite.json")
    private Resource gcpCredentials;

    @Bean
    public Storage storage() throws IOException {
        InputStream credentialsStream = gcpCredentials.getInputStream();
        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);
        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }
}