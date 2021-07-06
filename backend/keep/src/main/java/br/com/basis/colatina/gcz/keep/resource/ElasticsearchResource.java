package br.com.basis.colatina.gcz.keep.resource;

import br.com.basis.colatina.gcz.keep.service.elasticsearch.ElasticsearchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/elasticsearch/reindex")
public class ElasticsearchResource {

    private final ElasticsearchService elasticsearchService;

    @GetMapping
    public ResponseEntity<Void> reindexAll() {
        log.debug("Requisição rest para todas as entidades");
        elasticsearchService.reindexAll();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{indexName}")
    public ResponseEntity<Void> reindexOne(@PathVariable String indexName) {
        log.debug("Requisição rest para reindexar a entidade {}", indexName);
        elasticsearchService.reindexOne(indexName);
        return ResponseEntity.ok().build();
    }

}
