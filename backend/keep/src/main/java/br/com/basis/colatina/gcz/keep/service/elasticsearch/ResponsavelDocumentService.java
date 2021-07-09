package br.com.basis.colatina.gcz.keep.service.elasticsearch;

import br.com.basis.colatina.gcz.keep.domain.document.ResponsavelDocument;
import br.com.basis.colatina.gcz.keep.repository.ResponsavelRepository;
import br.com.basis.colatina.gcz.keep.repository.elasticsearch.ResponsavelDocumentRepository;
import br.com.basis.colatina.gcz.keep.service.event.ResponsavelEvent;
import br.com.basis.colatina.gcz.keep.service.filter.ResponsavelFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.Set;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.ObjectUtils.isEmpty;

@RequiredArgsConstructor
@Service
public class ResponsavelDocumentService {

    private final ResponsavelRepository responsavelRepository;
    private final ResponsavelDocumentRepository responsavelDocumentRepository;

    private final ElasticsearchOperations elasticsearchOperations;

    public Page<ResponsavelDocument> findAll(ResponsavelFilter responsavelFilter, Pageable pageable) {
        var resultado = elasticsearchOperations.search(responsavelFilter.getPagedQuery(pageable), ResponsavelDocument.class).stream()
                .map(SearchHit::getContent)
                .collect(toList());
        var total = elasticsearchOperations.count(responsavelFilter.getQuery(), ResponsavelDocument.class);

        return new PageImpl<>(resultado, pageable, total);
    }

    @TransactionalEventListener(fallbackExecution = true)
    public void indexResponsavel(ResponsavelEvent event) {
        var idEvent = event.getId();

        if (isEmpty(event.getIds())) {
            indexDocument(idEvent);
        } else {
            indexDocuments(event.getIds());
        }
    }

    private void indexDocument(Long idEvent) {
        responsavelRepository.getDocument(idEvent).ifPresentOrElse(
                responsavelDocumentRepository::save,
                () -> responsavelDocumentRepository.deleteById(idEvent));
    }

    private void indexDocuments(Set<Long> idsEvents) {
        var documents = responsavelRepository.getDocuments(idsEvents);

        if (isEmpty(documents)) {
            responsavelDocumentRepository.deleteAllById(idsEvents);
        } else {
            responsavelDocumentRepository.saveAll(documents);
        }
    }

}
