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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Service
public class ResponsavelDocumentService {

    private final ResponsavelRepository responsavelRepository;
    private final ResponsavelDocumentRepository responsavelDocumentRepository;

    private final ElasticsearchOperations elasticsearchOperations;

    @Transactional(readOnly = true)
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

        responsavelRepository.getDocument(idEvent).ifPresentOrElse(
                responsavelDocumentRepository::save,
                () -> responsavelDocumentRepository.deleteById(idEvent));
    }

}
