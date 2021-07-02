package br.com.basis.colatina.gcz.keep.service.mapper;

import br.com.basis.colatina.gcz.keep.domain.Responsavel;
import br.com.basis.colatina.gcz.keep.service.dto.ResponsavelDTO;
import br.com.basis.colatina.gcz.keep.service.mapper.common.EntityMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsavelMapper extends EntityMapper<ResponsavelDTO, Responsavel> {
}
