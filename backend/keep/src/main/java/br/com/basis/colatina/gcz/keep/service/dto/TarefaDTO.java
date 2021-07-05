package br.com.basis.colatina.gcz.keep.service.dto;

import br.com.basis.colatina.gcz.keep.domain.enumeration.TipoTarefaEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TarefaDTO {

    private Long id;
    private Long idResponsavel;
    private String titulo;
    private String descricao;
    private TipoTarefaEnum tipo;
    private LocalDateTime dataInicioPrevista;
    private LocalDateTime dataFimPrevista;
    private Integer tempoPrevisto;
    private Integer tempoGasto;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;

}
