package br.com.basis.colatina.gcz.keep.service.mapper;

import br.com.basis.colatina.gcz.keep.domain.Tarefa;
import br.com.basis.colatina.gcz.keep.service.dto.TarefaDTO;
import br.com.basis.colatina.gcz.keep.service.mapper.common.EntityMapper;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefaMapper extends EntityMapper<TarefaDTO, Tarefa> {

    @Override
    @Mapping(target = "idResponsavel", source = "responsavel.id")
    TarefaDTO toDto(Tarefa entity);

    @Override
    @InheritInverseConfiguration
    Tarefa toEntity(TarefaDTO dto);

}
