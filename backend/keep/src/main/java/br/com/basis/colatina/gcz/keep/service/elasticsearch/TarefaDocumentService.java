package br.com.basis.colatina.gcz.keep.service.elasticsearch;

import br.com.basis.colatina.gcz.keep.domain.document.TarefaDocument;
import br.com.basis.colatina.gcz.keep.repository.TarefaRepository;
import br.com.basis.colatina.gcz.keep.repository.elasticsearch.TarefaDocumentRepository;
import br.com.basis.colatina.gcz.keep.service.event.TarefaEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@RequiredArgsConstructor
@Service
public class TarefaDocumentService {

    private final TarefaDocumentRepository tarefaDocumentRepository;

    private final TarefaRepository tarefaRepository;

    @Transactional(readOnly = true)
    public Page<TarefaDocument> findAll(Pageable pageable) {
        return tarefaDocumentRepository.findAll(pageable);
    }

    @TransactionalEventListener(fallbackExecution = true)
    public void indexTarefa(TarefaEvent event) {
        var idEvent = event.getId();

        tarefaRepository.findById(idEvent).ifPresentOrElse(
                tarefa -> tarefaDocumentRepository.save(tarefaRepository.getDocument(idEvent)),
                () -> tarefaDocumentRepository.deleteById(idEvent));
    }

}
