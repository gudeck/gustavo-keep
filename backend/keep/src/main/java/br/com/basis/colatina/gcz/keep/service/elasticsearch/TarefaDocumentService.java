package br.com.basis.colatina.gcz.keep.service.elasticsearch;

import br.com.basis.colatina.gcz.keep.domain.document.TarefaDocument;
import br.com.basis.colatina.gcz.keep.repository.TarefaRepository;
import br.com.basis.colatina.gcz.keep.repository.elasticsearch.TarefaDocumentRepository;
import br.com.basis.colatina.gcz.keep.service.event.TarefaEvent;
import br.com.basis.colatina.gcz.keep.service.filter.TarefaFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class TarefaDocumentService {

    private final TarefaRepository tarefaRepository;
    private final TarefaDocumentRepository tarefaDocumentRepository;

    private final ElasticsearchOperations elasticsearchOperations;

    @Transactional(readOnly = true)
    public Page<TarefaDocument> findAll(TarefaFilter tarefaFilter, Pageable pageable) {
        var resultado = elasticsearchOperations.search(tarefaFilter.getPagedQuery(pageable), TarefaDocument.class).stream()
                .map(SearchHit::getContent)
                .collect(toList());
        var total = elasticsearchOperations.count(tarefaFilter.getQuery(), TarefaDocument.class);

        return new PageImpl<>(resultado, pageable, total);
    }

    @TransactionalEventListener(fallbackExecution = true)
    public void indexTarefa(TarefaEvent event) {
        var idEvent = event.getId();

        tarefaRepository.getDocument(idEvent).ifPresentOrElse(
                tarefaDocumentRepository::save,
                () -> tarefaDocumentRepository.deleteById(idEvent));
    }

}
