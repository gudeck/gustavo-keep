package br.com.basis.colatina.gcz.keep.repository;

import br.com.basis.colatina.gcz.keep.domain.Tarefa;
import br.com.basis.colatina.gcz.keep.domain.document.TarefaDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>, JpaSpecificationExecutor<Tarefa> {

    @Query("select new br.com.basis.colatina.gcz.keep.domain.document.TarefaDocument" +
            "(t.id, t.responsavel.id, t.responsavel.nome, t.titulo, t.tipo, t.dataInicioPrevista, t.dataFimPrevista, t.dataInicio, t.dataFim) " +
            "from Tarefa t " +
            "where t.id = :idTarefa ")
    TarefaDocument getDocument(@Param("idTarefa") Long idTarefa);

}
