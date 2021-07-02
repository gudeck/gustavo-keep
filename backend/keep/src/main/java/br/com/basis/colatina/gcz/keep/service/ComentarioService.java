package br.com.basis.colatina.gcz.keep.service;

import br.com.basis.colatina.gcz.keep.repository.ComentarioRepository;
import br.com.basis.colatina.gcz.keep.service.dto.ComentarioDTO;
import br.com.basis.colatina.gcz.keep.service.mapper.ComentarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioMapper comentarioMapper;
    private final ComentarioRepository comentarioRepository;

    public void deleteById(Long idComentario) {
        comentarioRepository.deleteById(idComentario);
    }

    public List<ComentarioDTO> findByIdTarefa(Long idTarefa) {
        return comentarioRepository.findAllByTarefaId(idTarefa).stream()
                .map(comentarioMapper::toDto)
                .collect(toList());
    }

    public ComentarioDTO save(Long idTarefa, ComentarioDTO comentarioDTO) {
        comentarioDTO.setIdTarefa(idTarefa);

        var comentario = comentarioMapper.toEntity(comentarioDTO);
        return comentarioMapper.toDto(comentarioRepository.save(comentario));
    }

}
