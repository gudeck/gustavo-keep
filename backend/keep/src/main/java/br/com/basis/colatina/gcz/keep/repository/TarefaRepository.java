package br.com.basis.colatina.gcz.keep.repository;

import br.com.basis.colatina.gcz.keep.domain.Tarefa;
import br.com.basis.colatina.gcz.keep.domain.document.TarefaDocument;
import br.com.basis.colatina.gcz.keep.repository.elasticsearch.Reindexator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>, JpaSpecificationExecutor<Tarefa>, Reindexator<TarefaDocument> {

    @Override
    @Query("select new br.com.basis.colatina.gcz.keep.domain.document.TarefaDocument" +
            "(t.id, t.responsavel.id, t.responsavel.nome, t.titulo, t.tipo, t.dataInicioPrevista, t.dataFimPrevista, t.dataInicio, t.dataFim) " +
            "from Tarefa t " +
            "where t.id = :idTarefa ")
    Optional<TarefaDocument> getDocument(@Param("idTarefa") Long idTarefa);

    @Override
    @Query("select new br.com.basis.colatina.gcz.keep.domain.document.TarefaDocument" +
            "(t.id, t.responsavel.id, t.responsavel.nome, t.titulo, t.tipo, t.dataInicioPrevista, t.dataFimPrevista, t.dataInicio, t.dataFim) " +
            "from Tarefa t ")
    Page<TarefaDocument> getDocuments(Pageable pageable);
}
