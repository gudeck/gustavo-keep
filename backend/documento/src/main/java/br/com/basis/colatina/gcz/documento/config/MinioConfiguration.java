package br.com.basis.colatina.gcz.documento.config;

import br.com.basis.colatina.gcz.documento.config.property.ApplicationProperties;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class MinioConfiguration {

    private final ApplicationProperties applicationProperties;

    @SneakyThrows
    private boolean bucketExists(MinioClient minioClient, String bucketName) {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
    }

    @SneakyThrows
    private void createBucket(MinioClient minioClient) {
        var bucket = applicationProperties.getMinio().getBucket();
        if (!bucketExists(minioClient, bucket)) {
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucket).build());
        }
    }

    @Bean
    public MinioClient minioClient() {
        var minioProperties = applicationProperties.getMinio();
        var minioClient = MinioClient.builder()
                .endpoint(minioProperties.getUrl())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
        createBucket(minioClient);
        return minioClient;
    }

}
