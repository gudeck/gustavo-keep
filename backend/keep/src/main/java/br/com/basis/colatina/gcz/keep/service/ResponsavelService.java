package br.com.basis.colatina.gcz.keep.service;

import br.com.basis.colatina.gcz.keep.repository.ResponsavelRepository;
import br.com.basis.colatina.gcz.keep.service.dto.ResponsavelDTO;
import br.com.basis.colatina.gcz.keep.service.mapper.ResponsavelMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ResponsavelService {

    private final ResponsavelMapper responsavelMapper;
    private final ResponsavelRepository responsavelRepository;

    public void deleteById(Long idResponsavel) {
        responsavelRepository.deleteById(idResponsavel);
    }

    public ResponsavelDTO findById(Long idResponsavel) {
        return responsavelRepository.findById(idResponsavel)
                .map(responsavelMapper::toDto)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "registro.nao-encontrado"));
    }

    public ResponsavelDTO save(ResponsavelDTO responsavelDTO) {
        var responsavel = responsavelMapper.toEntity(responsavelDTO);
        return responsavelMapper.toDto(responsavelRepository.save(responsavel));
    }

}
