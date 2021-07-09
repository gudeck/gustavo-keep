package br.com.basis.colatina.gcz.keep.service.filter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import java.time.LocalDate;

@Getter
@Setter
public class TarefaFilter implements BaseFilter {

    private String responsavel;
    private String titulo;
    private String tipo;
    private String dataInicioPrevista;
    private String dataFimPrevista;
    private String dataInicio;
    private String dataFim;
    private String dataInicioPrevistaPeriodo;
    private String dataFimPrevistaPeriodo;
    private String dataInicioPeriodo;
    private String dataFimPeriodo;

    @Override
    public CriteriaQuery getQuery() {
        var minDate = LocalDate.of(0, 1, 1).toString();
        var maxDate = LocalDate.of(9999, 12, 31).toString();

        var criteria = new Criteria();
        criteria = addContains(criteria, "responsavel", responsavel);
        criteria = addContains(criteria, "titulo", titulo);
        criteria = addContains(criteria, "tipo", tipo);
        criteria = addEquals(criteria, "dataInicioPrevista", dataInicioPrevista);
        criteria = addEquals(criteria, "dataFimPrevista", dataFimPrevista);
        criteria = addEquals(criteria, "dataInicio", dataInicio);
        criteria = addEquals(criteria, "dataFim", dataFim);
        criteria = addBetween(criteria, "dataInicioPrevista", dataInicioPrevistaPeriodo, maxDate);
        criteria = addBetween(criteria, "dataFimPrevista", minDate, dataFimPrevistaPeriodo);
        criteria = addBetween(criteria, "dataInicio", dataInicioPeriodo, maxDate);
        criteria = addBetween(criteria, "dataFim", minDate, dataFimPeriodo);
        return new CriteriaQuery(criteria);
    }
}
