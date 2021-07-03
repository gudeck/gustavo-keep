package br.com.basis.colatina.gcz.keep.domain.document;

import br.com.basis.colatina.gcz.keep.domain.enumeration.TipoTarefaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.Id;
import java.time.LocalDate;

import static org.springframework.data.elasticsearch.annotations.DateFormat.basic_date;
import static org.springframework.data.elasticsearch.annotations.FieldType.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "tarefa")
public class TarefaDocument {

    @Id
    private Long id;
    private Long idResponsavel;
    private String titulo;
    private TipoTarefaEnum tipo;

    @Field(type = Date, format = basic_date)
    private LocalDate dataInicioPrevista;

    @Field(type = Date, format = basic_date)
    private LocalDate dataFimPrevista;

    @Field(type = Date, format = basic_date)
    private LocalDate dataInicio;

    @Field(type = Date, format = basic_date)
    private LocalDate dataFim;

}
