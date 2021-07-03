package br.com.basis.colatina.gcz.keep.repository.elasticsearch;

import br.com.basis.colatina.gcz.keep.domain.document.ResponsavelDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ResponsavelDocumentRepository extends ElasticsearchRepository<ResponsavelDocument, Long> {
}
