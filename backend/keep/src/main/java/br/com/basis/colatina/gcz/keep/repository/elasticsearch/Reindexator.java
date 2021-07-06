package br.com.basis.colatina.gcz.keep.repository.elasticsearch;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface Reindexator<D> {

    default Optional<D> getDocument(Long id) {
        throw new NotImplementedException("getDocument não implementado");
    }

    default Page<D> getDocuments(Pageable pageable) {
        throw new NotImplementedException("getDocuments não implementado");
    }
}
