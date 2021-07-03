package br.com.basis.colatina.gcz.documento.service;

import br.com.basis.colatina.gcz.documento.service.other.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DocumentoService {

    private final MinioService minioService;

    public void deleteByUuid(UUID uuid) {
        minioService.removeObject(uuid);
    }

    public byte[] findByUuid(UUID uuid) {
        return minioService.getObject(uuid);
    }

    public UUID save(MultipartFile file) {
        return minioService.putObject(file);
    }

}

