package br.com.basis.colatina.gcz.keep.domain.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.Id;
import java.time.LocalDate;

import static org.springframework.data.elasticsearch.annotations.DateFormat.basic_date;
import static org.springframework.data.elasticsearch.annotations.FieldType.Date;

@Getter
@Setter
@Document(indexName = "responsavel")
public class ResponsavelDocument {

    @Id
    private Long id;
    private String nome;
    private String email;

    @Field(type = Date, format = basic_date)
    private LocalDate dataNascimento;

}
