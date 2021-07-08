package br.com.basis.colatina.gcz.keep.service.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

@Getter
@Setter
public class ResponsavelFilter implements BaseFilter {

    private String nome;
    private String email;
    private String dataNascimento;

    @Override
    public CriteriaQuery getQuery() {
        var criteria = new Criteria();
        criteria = addContains(criteria, "nome", nome);
        criteria = addContains(criteria, "email", email);
        criteria = addEquals(criteria, "dataNascimento", dataNascimento);
        return new CriteriaQuery(criteria);
    }

}
