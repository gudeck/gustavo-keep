package br.com.basis.colatina.gcz.keep.service;

import br.com.basis.colatina.gcz.keep.domain.document.ResponsavelDocument;
import br.com.basis.colatina.gcz.keep.repository.ResponsavelRepository;
import br.com.basis.colatina.gcz.keep.service.dto.ResponsavelDTO;
import br.com.basis.colatina.gcz.keep.service.elasticsearch.ResponsavelDocumentService;
import br.com.basis.colatina.gcz.keep.service.event.ResponsavelEvent;
import br.com.basis.colatina.gcz.keep.service.mapper.ResponsavelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ResponsavelService {

    private final ResponsavelMapper responsavelMapper;
    private final ResponsavelRepository responsavelRepository;
    private final ResponsavelDocumentService responsavelDocumentService;

    private final ApplicationEventPublisher applicationEventPublisher;

    public void deleteById(Long idResponsavel) {
        responsavelRepository.deleteById(idResponsavel);
        applicationEventPublisher.publishEvent(new ResponsavelEvent(idResponsavel));
    }

    public Page<ResponsavelDocument> findAll(Pageable pageable) {
        return responsavelDocumentService.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public ResponsavelDTO findById(Long idResponsavel) {
        return responsavelRepository.findById(idResponsavel)
                .map(responsavelMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "registro.nao-encontrado"));
    }

    public ResponsavelDTO save(ResponsavelDTO responsavelDTO) {
        var responsavel = responsavelRepository.save(responsavelMapper.toEntity(responsavelDTO));
        applicationEventPublisher.publishEvent(new ResponsavelEvent(responsavel.getId()));
        return responsavelMapper.toDto(responsavel);
    }

}
