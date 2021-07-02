package br.com.basis.colatina.gcz.keep.repository;

import br.com.basis.colatina.gcz.keep.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ComentarioRepository extends JpaRepository<Comentario, Long>, JpaSpecificationExecutor<Comentario> {

    List<Comentario> findAllByTarefaId(Long idTarefa);
}
