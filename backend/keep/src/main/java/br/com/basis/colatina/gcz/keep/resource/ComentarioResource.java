package br.com.basis.colatina.gcz.keep.resource;

import br.com.basis.colatina.gcz.keep.service.ComentarioService;
import br.com.basis.colatina.gcz.keep.service.dto.ComentarioDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tarefa/{idTarefa}/comentario")
public class ComentarioResource {

    private final ComentarioService comentarioService;

    @PostMapping
    public ResponseEntity<ComentarioDTO> create(@PathVariable Long idTarefa, @RequestBody ComentarioDTO comentarioDTO) {
        log.debug("Requisição rest para criação de um comentário na tarefa de id {}", idTarefa);
        comentarioService.save(idTarefa, comentarioDTO);
        var uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/tarefa/{idTarefa}").buildAndExpand(idTarefa).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{idComentario}")
    public ResponseEntity<Void> delete(@PathVariable Long idTarefa, @PathVariable Long idComentario) {
        log.debug("Requisição rest para excluir um comentário da tarefa {} com o id: {}", idTarefa, idComentario);
        comentarioService.deleteById(idComentario);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<List<ComentarioDTO>> find(@PathVariable Long idTarefa) {
        log.debug("Requisição rest para buscar os comentários da tarefa de id {}", idTarefa);
        return ResponseEntity.ok(comentarioService.findByIdTarefa(idTarefa));
    }

    @PutMapping
    public ResponseEntity<ComentarioDTO> update(@PathVariable Long idTarefa, @RequestBody ComentarioDTO comentarioDTO) {
        log.debug("Requisição rest para alterar o o comentário {} da tarefa {} com os dados: {}", comentarioDTO.getId(), idTarefa, comentarioDTO);
        return ResponseEntity.ok(comentarioService.save(idTarefa, comentarioDTO));
    }

}
