package br.com.basis.colatina.gcz.keep.service.elasticsearch;

import br.com.basis.colatina.gcz.keep.repository.ResponsavelRepository;
import br.com.basis.colatina.gcz.keep.repository.elasticsearch.ResponsavelDocumentRepository;
import br.com.basis.colatina.gcz.keep.service.event.ResponsavelEvent;
import br.com.basis.colatina.gcz.keep.service.mapper.elasticsearch.ResponsavelDocumentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Service
public class ResponsavelDocumentService {


    private final ResponsavelDocumentMapper responsavelDocumentMapper;
    private final ResponsavelDocumentRepository responsavelDocumentRepository;

    private final ResponsavelRepository responsavelRepository;

    @TransactionalEventListener(fallbackExecution = true)
    public void indexResponsavel(ResponsavelEvent event) {
        var idEvent = event.getId();

        responsavelRepository.findById(idEvent).ifPresentOrElse(
                responsavel -> responsavelDocumentRepository.save(responsavelDocumentMapper.toDto(responsavel)),
                () -> responsavelDocumentRepository.deleteById(idEvent));
    }

}