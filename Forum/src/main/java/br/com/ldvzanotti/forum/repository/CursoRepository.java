package br.com.ldvzanotti.forum.repository;

import br.com.ldvzanotti.forum.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
	Curso findByNome (String nome);
}
