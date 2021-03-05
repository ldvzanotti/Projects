package br.com.ldvzanotti.forum.repository;

import br.com.ldvzanotti.forum.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
	//adicionar _ para demonstrar relacionamento e evitar ambiguidade
	List<Topico> findByCurso_Nome (String nomeCurso);
}
