package br.com.basis.colatina.gcz.documento.resource;

import br.com.basis.colatina.gcz.documento.service.DocumentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/documento")
@RequiredArgsConstructor
public class DocumentoResource {

    private final DocumentoService documentoService;

    @PostMapping
    public ResponseEntity<UUID> create(@RequestBody MultipartFile file) {
        return ResponseEntity.ok(documentoService.save(file));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable UUID uuid) {
        documentoService.deleteByUuid(uuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<byte[]> find(@PathVariable UUID uuid) {
        return ResponseEntity.ok(documentoService.findByUuid(uuid));
    }

}
