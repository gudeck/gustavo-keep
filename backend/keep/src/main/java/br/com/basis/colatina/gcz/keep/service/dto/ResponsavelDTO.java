package br.com.basis.colatina.gcz.keep.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ResponsavelDTO {

    private Long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;

}
