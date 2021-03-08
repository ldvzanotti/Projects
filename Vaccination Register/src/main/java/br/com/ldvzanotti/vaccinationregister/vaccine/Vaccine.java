package br.com.ldvzanotti.vaccinationregister.vaccine;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Objects;

@Entity
public class Vaccine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank @Length(min = 5)
	private String name;
	@NotNull @Max(3)
	private Integer dose;
	@NotBlank @Length(min = 10)
	private String userEmail;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy",
			locale = "pt-BR", timezone = "Brazil/East")
	private Calendar date;

	public Vaccine (String name, Integer dose, String userEmail, Calendar date) {
		this.name = name;
		this.dose = dose;
		this.userEmail = userEmail;
		this.date = date;
	}

	public Vaccine() {

	}

	@Override
	public boolean equals (Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Vaccine vaccine = (Vaccine) o;
		return Objects.equals(getId(), vaccine.getId()) && Objects.equals(getName(), vaccine.getName()) && Objects.equals(getDose(), vaccine.getDose()) && Objects.equals(getUserEmail(), vaccine.getUserEmail()) && Objects.equals(getDate(), vaccine.getDate());
	}

	@Override
	public int hashCode () {
		return Objects.hash(getId(), getName(), getDose(), getUserEmail(), getDate());
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

	public Integer getDose () {
		return dose;
	}

	public void setDose (Integer dose) {
		this.dose = dose;
	}

	public String getUserEmail () {
		return userEmail;
	}

	public void setUserEmail (String userEmail) {
		this.userEmail = userEmail;
	}

	public Calendar getDate () {
		return date;
	}

	public void setDate (Calendar date) {
		this.date = date;
	}
}
