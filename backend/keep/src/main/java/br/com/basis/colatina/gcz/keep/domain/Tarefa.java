package br.com.basis.colatina.gcz.keep.domain;

import br.com.basis.colatina.gcz.keep.domain.enumeration.TipoTarefaEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;
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

    @Enumerated(STRING)
    @Column(nullable = false)
    private TipoTarefaEnum tipo;

    @Column(nullable = false)
    private LocalDateTime dataInicioPrevista;

    @Column(nullable = false)
    private LocalDateTime dataFimPrevista;

    private Integer tempoPrevisto;

    private Integer tempoGasto;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

}
