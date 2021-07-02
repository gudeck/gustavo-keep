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

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
public class Comentario {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_generator_comentario")
    @SequenceGenerator(name = "seq_generator_comentario", sequenceName = "seq_comentario", allocationSize = 1)
    private Long id;

    @JoinColumn(name = "id_tarefa")
    @ManyToOne(fetch = LAZY, optional = false)
    private Tarefa tarefa;

    @Column(nullable = false)
    private String conteudo;

}
