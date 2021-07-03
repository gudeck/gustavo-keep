package br.com.basis.colatina.gcz.keep.service;

import br.com.basis.colatina.gcz.keep.repository.AnexoRepository;
import br.com.basis.colatina.gcz.keep.service.dto.AnexoDTO;
import br.com.basis.colatina.gcz.keep.service.feign.DocumentoClient;
import br.com.basis.colatina.gcz.keep.service.mapper.AnexoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AnexoService {

    private final AnexoMapper anexoMapper;
    private final AnexoRepository anexoRepository;

    private final TarefaService tarefaService;

    private final DocumentoClient documentoClient;

    public void deleteByUuid(UUID uuid) {
        documentoClient.delete(uuid);
        anexoRepository.deleteById(uuid);
    }

    public AnexoDTO findByUuid(UUID uuid) {
        return anexoRepository.findById(uuid)
                .map(anexo -> {
                    var anexoDTO = anexoMapper.toDto(anexo);
                    anexoDTO.setFile(documentoClient.find(uuid));
                    return anexoDTO;
                })
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "registro.nao-encontrado"));
    }

    public void save(Long idTarefa, MultipartFile file) {
        if (tarefaService.existsById(idTarefa)) {
            var anexoDTO = AnexoDTO.builder()
                    .uuid(documentoClient.create(file))
                    .idTarefa(idTarefa)
                    .nome(file.getOriginalFilename())
                    .build();
            anexoRepository.save(anexoMapper.toEntity(anexoDTO));
        }
    }

}
