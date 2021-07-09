package br.com.basis.colatina.gcz.keep.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TarefaDTO {

    private Long id;
    private Long idResponsavel;
    private String titulo;
    private String descricao;
    private String tipo;
    private LocalDate dataInicioPrevista;
    private LocalDate dataFimPrevista;
    private Integer tempoPrevisto;
    private Integer tempoGasto;
    private LocalDate dataInicio;
    private LocalDate dataFim;

}
