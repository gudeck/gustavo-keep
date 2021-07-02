package br.com.basis.colatina.gcz.keep.service;

import br.com.basis.colatina.gcz.keep.repository.AnexoRepository;
import br.com.basis.colatina.gcz.keep.service.dto.AnexoDTO;
import br.com.basis.colatina.gcz.keep.service.mapper.AnexoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class AnexoService {

    private final AnexoMapper anexoMapper;
    private final AnexoRepository anexoRepository;

    public void deleteByUuid(UUID uuid) {
        anexoRepository.deleteById(uuid);
    }

    public UUID findByUuid(UUID uuid) {
        return anexoRepository.findById(uuid)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "registro.nao-encontrado"))
                .getUuid();
    }

    public void save(Long idTarefa, MultipartFile file) {
        anexoRepository.save(anexoMapper.toEntity(AnexoDTO.builder()
                .idTarefa(idTarefa)
                .uuid(randomUUID()).build()));
    }

}
