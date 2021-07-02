package br.com.basis.colatina.gcz.keep.service.mapper.common;

import java.util.List;

public interface EntityMapper<D, E> {

    D toDto(E entidade);

    List<D> toDto(List<E> entidades);

    E toEntity(D dto);

    List<E> toEntity(List<D> dtos);

}
