package br.com.basis.colatina.gcz.keep.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComentarioDTO {

    private Long id;
    private Long idTarefa;
    private String conteudo;

}
