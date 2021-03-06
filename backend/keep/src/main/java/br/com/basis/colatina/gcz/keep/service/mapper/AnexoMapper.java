package br.com.basis.colatina.gcz.keep.service.mapper;

import br.com.basis.colatina.gcz.keep.domain.Anexo;
import br.com.basis.colatina.gcz.keep.service.dto.AnexoDTO;
import br.com.basis.colatina.gcz.keep.service.mapper.common.EntityMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnexoMapper extends EntityMapper<AnexoDTO, Anexo> {

    @Override
    @Mapping(target = "idTarefa", source = "tarefa.id")
    AnexoDTO toDto(Anexo entity);

    @Override
    @InheritInverseConfiguration
    Anexo toEntity(AnexoDTO dto);

}
