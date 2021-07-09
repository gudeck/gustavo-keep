package br.com.basis.colatina.gcz.keep.domain.document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import javax.persistence.Id;
import java.time.LocalDate;

import static org.springframework.data.elasticsearch.annotations.DateFormat.date;
import static org.springframework.data.elasticsearch.annotations.DateFormat.date_hour_minute_second;
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

    private String responsavel;

    private String titulo;

    private String tipo;

    @MultiField(mainField = @Field(type = Date, format = date_hour_minute_second),
            otherFields = {@InnerField(suffix = "localDate", type = Date, format = date)})
    private LocalDate dataInicioPrevista;

    @MultiField(mainField = @Field(type = Date, format = date_hour_minute_second),
            otherFields = {@InnerField(suffix = "localDate", type = Date, format = date)})
    private LocalDate dataFimPrevista;

    @MultiField(mainField = @Field(type = Date, format = date_hour_minute_second),
            otherFields = {@InnerField(suffix = "localDate", type = Date, format = date)})
    private LocalDate dataInicio;

    @MultiField(mainField = @Field(type = Date, format = date_hour_minute_second),
            otherFields = {@InnerField(suffix = "localDate", type = Date, format = date)})
    private LocalDate dataFim;

}
