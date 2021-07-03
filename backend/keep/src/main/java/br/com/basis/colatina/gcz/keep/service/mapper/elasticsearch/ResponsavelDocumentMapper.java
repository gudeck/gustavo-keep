package br.com.basis.colatina.gcz.keep.service.mapper.elasticsearch;

import br.com.basis.colatina.gcz.keep.domain.Responsavel;
import br.com.basis.colatina.gcz.keep.domain.document.ResponsavelDocument;
import br.com.basis.colatina.gcz.keep.service.mapper.common.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsavelDocumentMapper extends EntityMapper<ResponsavelDocument, Responsavel> {
}
