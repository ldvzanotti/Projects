package br.com.ldvzanotti.vaccinationregister.user;

import java.util.Calendar;

public class UserDto {

	private Long id;
	private String name;
	private String email;
	private String cpf;
	private Calendar birth;

	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.cpf = user.getCpf();
		this.birth = user.getBirth();
	}

	public UserDto() {

	}

	public User create () {
		return new User(name, email, cpf, birth);
	}

	public Long getId () {
		return id;
	}

	public String getName () {
		return name;
	}

	public String getEmail () {
		return email;
	}

	public String getCpf () {
		return cpf;
	}

	public Calendar getBirth () {
		return birth;
	}
}
