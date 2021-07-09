package br.com.basis.colatina.gcz.keep.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_generator_tarefa")
    @SequenceGenerator(name = "seq_generator_tarefa", sequenceName = "seq_tarefa", allocationSize = 1)
    private Long id;

    @JoinColumn(name = "id_responsavel")
    @ManyToOne(fetch = LAZY, optional = false)
    private Responsavel responsavel;

    @Column(nullable = false)
    private String titulo;

    private String descricao;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private LocalDate dataInicioPrevista;

    @Column(nullable = false)
    private LocalDate dataFimPrevista;

    private Integer tempoPrevisto;

    private Integer tempoGasto;

    private LocalDate dataInicio;

    private LocalDate dataFim;

}
