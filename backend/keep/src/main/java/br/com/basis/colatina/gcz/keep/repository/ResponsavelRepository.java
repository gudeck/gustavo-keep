package br.com.basis.colatina.gcz.keep.repository;

import br.com.basis.colatina.gcz.keep.domain.Responsavel;
import br.com.basis.colatina.gcz.keep.domain.document.ResponsavelDocument;
import br.com.basis.colatina.gcz.keep.repository.elasticsearch.Reindexator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long>, JpaSpecificationExecutor<Responsavel>, Reindexator<ResponsavelDocument> {

    @Override
    @Query("select new br.com.basis.colatina.gcz.keep.domain.document.ResponsavelDocument" +
            "(r.id, r.nome, r.email, r.dataNascimento) " +
            "from Responsavel r " +
            "where r.id = :idResponsavel ")
    Optional<ResponsavelDocument> getDocument(@Param("idResponsavel") Long idResponsavel);

    @Query("select new br.com.basis.colatina.gcz.keep.domain.document.ResponsavelDocument" +
            "(r.id, r.nome, r.email, r.dataNascimento) " +
            "from Responsavel r " +
            "where r.id in (:idsResponsaveis) ")
    List<ResponsavelDocument> getDocuments(@Param("idsResponsaveis") Set<Long> idsResponsaveis);

    @Override
    @Query("select new br.com.basis.colatina.gcz.keep.domain.document.ResponsavelDocument" +
            "(r.id, r.nome, r.email, r.dataNascimento) " +
            "from Responsavel r ")
    Page<ResponsavelDocument> getDocuments(Pageable pageable);
}
