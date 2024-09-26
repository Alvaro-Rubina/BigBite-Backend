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
        // Opción 1: Usar el nombre original del archivo y agregarle una fecha para hacerlo único
        String nombreOriginal = file.getOriginalFilename();

        if (nombreOriginal != null) {
            nombreOriginal = nombreOriginal.replaceAll("\\s+", "_"); // Reemplazar espacios con guiones bajos
        }

        String fecha = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = nombreOriginal != null
                ? nombreOriginal + "_" + fecha  // Si existe un nombre original, lo usa
                : UUID.randomUUID().toString() + "_" + fecha;  // Si no existe, usa un UUID

        // Aquí especificas el formato de imagen (podría ser "jpeg" o "png" dependiendo del archivo)
        String fileExtension = getFileExtension(file);  // Obtener la extensión del archivo subido
        fileName += "." + fileExtension;  // Añadir la extensión correcta

        BlobInfo blobInfo = storage.create(
                BlobInfo.newBuilder(bucketName, fileName)
                        .setContentType(file.getContentType())  // Usar el tipo de contenido correcto
                        .build(),
                file.getInputStream()
        );

        // Devolver la URL del archivo subido
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
    }

    // Método para obtener la extensión del archivo
    private String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
        }
        return "jpg";  // Valor por defecto si no se encuentra la extensión
    }
}
