package br.com.basis.colatina.gcz.keep.repository.elasticsearch;

import br.com.basis.colatina.gcz.keep.domain.document.TarefaDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TarefaDocumentRepository extends ElasticsearchRepository<TarefaDocument, Long> {
}
