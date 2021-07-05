package br.com.basis.colatina.gcz.keep.domain.document;

import br.com.basis.colatina.gcz.keep.domain.enumeration.TipoTarefaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.Id;
import java.time.LocalDateTime;

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
    private String nomeResponsavel;
    private String titulo;
    private TipoTarefaEnum tipo;

    @Field(type = Date, format = date_hour_minute_second)
    private LocalDateTime dataInicioPrevista;

    @Field(type = Date, format = date_hour_minute_second)
    private LocalDateTime dataFimPrevista;

    @Field(type = Date, format = date_hour_minute_second)
    private LocalDateTime dataInicio;

    @Field(type = Date, format = date_hour_minute_second)
    private LocalDateTime dataFim;

}
