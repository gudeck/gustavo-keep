package br.com.basis.colatina.gcz.keep.resource;

import br.com.basis.colatina.gcz.keep.service.AnexoService;
import br.com.basis.colatina.gcz.keep.service.dto.AnexoDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tarefa/{idTarefa}/anexo")
public class AnexoResource {

    private final AnexoService anexoService;

    @PostMapping
    public ResponseEntity<AnexoDTO> create(@PathVariable Long idTarefa, @RequestBody MultipartFile file) {
        log.debug("Requisição rest para criação de um anexo na tarefa de id {}", idTarefa);
        anexoService.save(idTarefa, file);
        var uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/tarefa/{idTarefa}").buildAndExpand(idTarefa).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> delete(@PathVariable Long idTarefa, @PathVariable UUID uuid) {
        log.debug("Requisição rest para excluir um anexo da tarefa {} com o uuid: {}", idTarefa, uuid);
        anexoService.deleteByUuid(uuid);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UUID> find(@PathVariable Long idTarefa, @PathVariable UUID uuid) {
        log.debug("Requisição rest para buscar um anexo da tarefa {} com o uuid: {}", idTarefa, uuid);
        return ResponseEntity.ok(anexoService.findByUuid(uuid));
    }

}
