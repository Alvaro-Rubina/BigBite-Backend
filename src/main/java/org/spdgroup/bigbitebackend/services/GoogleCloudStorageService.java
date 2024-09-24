package org.spdgroup.bigbitebackend.services;

import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class GoogleCloudStorageService {

    private final Storage storage = StorageOptions.getDefaultInstance().getService();
    private final String bucketName = "big-bite-bucket";

    public String uploadFile(MultipartFile file) throws IOException {
        String nombreBase = "gero-momo"; // Nombre que quieres que aparezca
        String fecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = nombreBase + "_" + fecha + ".jpg"; // Formato: gero-momo_20230923_123456.jpg

        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, fileName)
                        .setContentType("image/jpeg")
                        .build(),
                file.getInputStream()
        );

        return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
    }
}