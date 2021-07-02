package br.com.basis.colatina.gcz.keep.service.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Getter
@Setter
@Builder
public class AnexoDTO {

    private UUID uuid;
    private Long idTarefa;
    private MultipartFile file;

}
