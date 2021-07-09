package br.com.basis.colatina.gcz.keep.resource;

import br.com.basis.colatina.gcz.keep.domain.document.ResponsavelDocument;
import br.com.basis.colatina.gcz.keep.service.ResponsavelService;
import br.com.basis.colatina.gcz.keep.service.dto.ResponsavelDTO;
import br.com.basis.colatina.gcz.keep.service.elasticsearch.ResponsavelDocumentService;
import br.com.basis.colatina.gcz.keep.service.filter.ResponsavelFilter;
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
@RequestMapping("/api/responsavel")
public class ResponsavelResource {

    private final ResponsavelService responsavelService;
    private final ResponsavelDocumentService responsavelDocumentService;

    @PostMapping
    public ResponseEntity<ResponsavelDTO> create(@RequestBody ResponsavelDTO responsavelDTO) {
        log.debug("Requisição rest para criação de um responsável com os dados: {}", responsavelDTO);
        ResponsavelDTO responsavel = responsavelService.save(responsavelDTO);
        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idResponsavel}").buildAndExpand(responsavel.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/bulk")
    public ResponseEntity<Void> bulkCreate(@RequestBody List<ResponsavelDTO> responsaveisDTO) {
        log.debug("Requisição rest para criação de um responsável com os dados: {}", responsaveisDTO);
        responsavelService.saveAll(responsaveisDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idResponsavel}")
    public ResponseEntity<ResponsavelDTO> delete(@PathVariable Long idResponsavel) {
        log.debug("Requisição rest para excluir um responsável com o id: {}", idResponsavel);
        responsavelService.deleteById(idResponsavel);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idResponsavel}")
    public ResponseEntity<ResponsavelDTO> find(@PathVariable Long idResponsavel) {
        log.debug("Requisição rest para buscar um responsável com o id: {}", idResponsavel);
        return ResponseEntity.ok(responsavelService.findById(idResponsavel));
    }

    @GetMapping
    public ResponseEntity<Page<ResponsavelDocument>> findAll(ResponsavelFilter responsavelFilter, Pageable pageable) {
        log.debug("Requisição rest para listar responsáveis");
        return ResponseEntity.ok(responsavelDocumentService.findAll(responsavelFilter, pageable));
    }

    @PutMapping
    public ResponseEntity<ResponsavelDTO> update(@RequestBody ResponsavelDTO responsavelDTO) {
        log.debug("Requisição rest para alterar o responsável de id {} com os dados: {}", responsavelDTO.getId(), responsavelDTO);
        return ResponseEntity.ok(responsavelService.save(responsavelDTO));
    }
}
