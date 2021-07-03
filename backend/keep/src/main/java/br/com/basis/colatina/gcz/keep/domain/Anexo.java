package br.com.basis.colatina.gcz.keep.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
public class Anexo {

    String nome;
    @Id
    private UUID uuid;
    @JoinColumn(name = "id_tarefa")
    @ManyToOne(fetch = LAZY, optional = false)
    private Tarefa tarefa;

}
