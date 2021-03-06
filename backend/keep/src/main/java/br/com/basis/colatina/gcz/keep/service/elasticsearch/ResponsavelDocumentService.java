package br.com.basis.colatina.gcz.keep.service.elasticsearch;

import br.com.basis.colatina.gcz.keep.domain.document.ResponsavelDocument;
import br.com.basis.colatina.gcz.keep.repository.ResponsavelRepository;
import br.com.basis.colatina.gcz.keep.repository.elasticsearch.ResponsavelDocumentRepository;
import br.com.basis.colatina.gcz.keep.service.event.ResponsavelEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Service
public class ResponsavelDocumentService {

    private final ResponsavelDocumentRepository responsavelDocumentRepository;

    private final ResponsavelRepository responsavelRepository;

    @Transactional(readOnly = true)
    public Page<ResponsavelDocument> findAll(Pageable pageable) {
        return responsavelDocumentRepository.findAll(pageable);
    }

    @TransactionalEventListener(fallbackExecution = true)
    public void indexResponsavel(ResponsavelEvent event) {
        var idEvent = event.getId();

        responsavelRepository.getDocument(idEvent).ifPresentOrElse(
                responsavelDocumentRepository::save,
                () -> responsavelDocumentRepository.deleteById(idEvent));
    }

}
