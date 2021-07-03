package br.com.basis.colatina.gcz.keep.service.mapper;

import br.com.basis.colatina.gcz.keep.domain.Comentario;
import br.com.basis.colatina.gcz.keep.service.dto.ComentarioDTO;
import br.com.basis.colatina.gcz.keep.service.mapper.common.EntityMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ComentarioMapper extends EntityMapper<ComentarioDTO, Comentario> {

    @Override
    @Mapping(target = "idTarefa", source = "tarefa.id")
    ComentarioDTO toDto(Comentario entity);

    @Override
    @InheritInverseConfiguration
    Comentario toEntity(ComentarioDTO dto);

}
