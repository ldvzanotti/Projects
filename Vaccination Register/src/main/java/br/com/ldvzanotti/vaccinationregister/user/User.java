package br.com.ldvzanotti.vaccinationregister.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Objects;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank @Length(min = 8)
	private String name;
	@NotBlank @Length(min = 10)
	private String email;
	@NotBlank @Length(min = 11, max = 11)
	private String cpf;
	@NotNull
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",
			locale = "pt-BR", timezone = "Brazil/East")
	private Calendar birth;

	public User (String name, String email, String cpf, Calendar birth) {
		this.name = name;
		this.email = email;
		this.cpf = cpf;
		this.birth = birth;
	}

	public User () {

	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return id.equals(user.id) && name.equals(user.name) && email.equals(user.email) && cpf.equals(user.cpf) && birth.equals(user.birth);
	}

	@Override
	public int hashCode () {
		return Objects.hash(id, name, email, cpf, birth);
	}

	public Long getId () {
		return id;
	}

	public void setId (Long id) {
		this.id = id;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}

	public String getEmail () {
		return email;
	}

	public void setEmail (String email) {
		this.email = email;
	}

	public String getCpf () {
		return cpf;
	}

	public void setCpf (String cpf) {
		this.cpf = cpf;
	}

	public Calendar getBirth () {
		return birth;
	}

	public void setBirth (Calendar birth) {
		this.birth = birth;
	}
}
