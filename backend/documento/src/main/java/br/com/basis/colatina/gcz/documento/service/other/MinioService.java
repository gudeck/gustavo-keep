package br.com.basis.colatina.gcz.documento.service.other;

import br.com.basis.colatina.gcz.documento.config.property.ApplicationProperties;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class MinioService {

    private final ApplicationProperties applicationProperties;
    private final MinioClient minioClient;

    @SneakyThrows
    public byte[] getObject(UUID uuid) {
        var getObjectArgs = GetObjectArgs.builder()
                .bucket(applicationProperties.getMinio().getBucket())
                .object(uuid.toString())
                .build();
        return minioClient.getObject(getObjectArgs).readAllBytes();
    }

    @SneakyThrows
    public UUID putObject(MultipartFile file) {
        var uuid = randomUUID();
        var putObjectArgs = PutObjectArgs.builder()
                .bucket(applicationProperties.getMinio().getBucket())
                .object(uuid.toString())
                .contentType(file.getContentType())
                .stream(file.getInputStream(), file.getSize(), -1)
                .build();

        minioClient.putObject(putObjectArgs);
        return uuid;
    }

    @SneakyThrows
    public void removeObject(UUID uuid) {
        var removeObjectArgs = RemoveObjectArgs.builder()
                .bucket(applicationProperties.getMinio().getBucket())
                .object(uuid.toString())
                .build();
        minioClient.removeObject(removeObjectArgs);
    }

}

