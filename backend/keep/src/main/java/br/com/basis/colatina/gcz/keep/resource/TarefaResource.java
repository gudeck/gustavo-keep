package br.com.basis.colatina.gcz.keep.resource;

import br.com.basis.colatina.gcz.keep.domain.document.TarefaDocument;
import br.com.basis.colatina.gcz.keep.service.TarefaService;
import br.com.basis.colatina.gcz.keep.service.dto.TarefaDTO;
import br.com.basis.colatina.gcz.keep.service.elasticsearch.TarefaDocumentService;
import br.com.basis.colatina.gcz.keep.service.filter.TarefaFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/api/tarefa")
public class TarefaResource {

    private final TarefaService tarefaService;
    private final TarefaDocumentService tarefaDocumentService;

    @PostMapping
    public ResponseEntity<TarefaDTO> create(@RequestBody TarefaDTO tarefaDTO) {
        log.debug("Requisição rest para criação de uma tarefa com os dados: {}", tarefaDTO);
        TarefaDTO tarefa = tarefaService.save(tarefaDTO);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idTarefa}").buildAndExpand(tarefa.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/bulk")
    public ResponseEntity<Void> bulkCreate(@RequestBody List<TarefaDTO> tarefasDTO) {
        log.debug("Requisição rest para criação de uma tarefa com os dados: {}", tarefasDTO);
        tarefaService.saveAll(tarefasDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idTarefa}")
    public ResponseEntity<TarefaDTO> delete(@PathVariable Long idTarefa) {
        log.debug("Requisição rest para excluir uma tarefa com o id: {}", idTarefa);
        tarefaService.deleteById(idTarefa);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idTarefa}")
    public ResponseEntity<TarefaDTO> find(@PathVariable Long idTarefa) {
        log.debug("Requisição rest para buscar uma tarefa com o id: {}", idTarefa);
        return ResponseEntity.ok(tarefaService.findById(idTarefa));
    }

    @GetMapping
    public ResponseEntity<Page<TarefaDocument>> findAll(TarefaFilter tarefaFilter, Pageable pageable) {
        log.debug("Requisição rest para listar tarefas");
        return ResponseEntity.ok(tarefaDocumentService.findAll(tarefaFilter, pageable));
    }

    @PutMapping
    public ResponseEntity<TarefaDTO> update(@RequestBody TarefaDTO tarefaDTO) {
        log.debug("Requisição rest para alterar a tarefa de id {} com os dados: {}", tarefaDTO.getId(), tarefaDTO);
        return ResponseEntity.ok(tarefaService.save(tarefaDTO));
    }
}
