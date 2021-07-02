package br.com.basis.colatina.gcz.keep.service.mapper;

import br.com.basis.colatina.gcz.keep.domain.Tarefa;
import br.com.basis.colatina.gcz.keep.service.dto.TarefaDTO;
import br.com.basis.colatina.gcz.keep.service.mapper.common.EntityMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TarefaMapper extends EntityMapper<TarefaDTO, Tarefa> {

    @Mapping(target = "idResponsavel", source = "responsavel.id")
    TarefaDTO toDto(Tarefa entity);

    @Mapping(target = "responsavel.id", source = "idResponsavel")
    Tarefa toEntity(TarefaDTO dto);

}
