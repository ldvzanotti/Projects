package br.com.ldvzanotti.forum.controller.form;

import br.com.ldvzanotti.forum.model.Curso;
import br.com.ldvzanotti.forum.model.Topico;
import br.com.ldvzanotti.forum.repository.CursoRepository;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TopicoForm {
	@NotNull @NotEmpty @Size(min = 5)
	private String titulo;
	@NotNull @NotEmpty @Size(min = 5)
	private String mensagem;
	@NotNull @NotEmpty @Size(min = 5)
	private String nomeCurso;

	public String getTitulo () {
		return titulo;
	}

	public void setTitulo (String titulo) {
		this.titulo = titulo;
	}

	public String getMensagem () {
		return mensagem;
	}

	public void setMensagem (String mensagem) {
		this.mensagem = mensagem;
	}

	public String getNomeCurso () {
		return nomeCurso;
	}

	public void setNomeCurso (String nomeCurso) {
		this.nomeCurso = nomeCurso;
	}

	public Topico converter (CursoRepository cursoRepository) {
		Curso curso = cursoRepository.findByNome(nomeCurso);
		return new Topico(titulo, mensagem, curso);
	}
}
