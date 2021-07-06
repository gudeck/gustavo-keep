package br.com.basis.colatina.gcz.keep.repository.elasticsearch;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface Reindexator<D> {

    default Optional<D> getDocument(Long id) {
        throw new RuntimeException("getDocument não implementado");
    }

    default Page<D> getDocuments(Pageable pageable) {
        throw new RuntimeException("getDocuments não implementado");
    }
}
