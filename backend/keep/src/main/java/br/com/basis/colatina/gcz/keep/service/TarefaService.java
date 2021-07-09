package br.com.basis.colatina.gcz.keep.service;

import br.com.basis.colatina.gcz.keep.repository.TarefaRepository;
import br.com.basis.colatina.gcz.keep.service.dto.TarefaDTO;
import br.com.basis.colatina.gcz.keep.service.event.TarefaEvent;
import br.com.basis.colatina.gcz.keep.service.mapper.TarefaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaMapper tarefaMapper;
    private final TarefaRepository tarefaRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    public void deleteById(Long idTarefa) {
        tarefaRepository.deleteById(idTarefa);
        applicationEventPublisher.publishEvent(new TarefaEvent(idTarefa));
    }

    @Transactional(readOnly = true)
    public boolean existsById(Long idTarefa) {
        return tarefaRepository.existsById(idTarefa);
    }

    @Transactional(readOnly = true)
    public TarefaDTO findById(Long idTarefa) {
        return tarefaRepository.findById(idTarefa)
                .map(tarefaMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "registro.nao-encontrado"));
    }

    public TarefaDTO save(TarefaDTO tarefaDTO) {
        var tarefa = tarefaRepository.save(tarefaMapper.toEntity(tarefaDTO));
        applicationEventPublisher.publishEvent(new TarefaEvent(tarefa.getId()));
        return tarefaMapper.toDto(tarefa);
    }

}
