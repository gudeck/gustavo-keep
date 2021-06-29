package br.com.basis.colatina.gcz.keep.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.time.LocalDate;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@Entity
public class Responsavel {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "seq_generator_responsavel")
    @SequenceGenerator(name = "seq_generator_responsavel", sequenceName = "seq_responsavel", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate dataNascimento;

}
