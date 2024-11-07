package org.spdgroup.bigbitebackend.utils.gcloud;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class GoogleCloudConfig {

    @Bean
    public Storage storage() throws IOException {
        String credentialsJson = System.getenv("GCP_CREDENTIALS");
        System.out.println("Credenciales JSON: " + credentialsJson);

        if (credentialsJson == null) {
            throw new IllegalStateException("GCP_CREDENTIALS environment variable is not set");
        }

        // Decodifica correctamente los saltos de línea y los caracteres especiales en el JSON
        credentialsJson = credentialsJson.replace("\\n", "\n");

        InputStream credentialsStream = new ByteArrayInputStream(credentialsJson.getBytes(StandardCharsets.UTF_8));
        GoogleCredentials credentials = GoogleCredentials.fromStream(credentialsStream);

        return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
    }
}
