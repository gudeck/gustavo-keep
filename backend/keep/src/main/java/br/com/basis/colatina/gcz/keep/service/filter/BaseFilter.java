package br.com.basis.colatina.gcz.keep.service.filter;

import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public interface BaseFilter {

    CriteriaQuery getQuery();

    default CriteriaQuery getPagedQuery(Pageable pageable) {
        return getQuery().addSort(pageable.getSort()).setPageable(pageable);
    }

    default Criteria addContains(Criteria criteria, String field, String value) {
        return isNotBlank(value) ? criteria.and(field).contains(value) : criteria;
    }

    default Criteria addEquals(Criteria criteria, String field, String value) {
        return isNotBlank(value) ? criteria.and(field).is(value) : criteria;
    }

}