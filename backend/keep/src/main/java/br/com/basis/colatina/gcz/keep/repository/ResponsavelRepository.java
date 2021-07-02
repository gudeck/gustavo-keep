package br.com.basis.colatina.gcz.keep.repository;

import br.com.basis.colatina.gcz.keep.domain.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ResponsavelRepository extends JpaRepository<Responsavel, Long>, JpaSpecificationExecutor<Responsavel> {
}
