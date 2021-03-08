package br.com.ldvzanotti.vaccinationregister.vaccine;

import java.util.Calendar;

public class VaccineDto {

	private Long id;
	private String name;
	private Integer dose;
	private String userEmail;
	private Calendar date;

	public VaccineDto (Vaccine vaccine) {
		this.id = vaccine.getId();
		this.name = vaccine.getName();
		this.dose = vaccine.getDose();
		this.userEmail = vaccine.getUserEmail();
		this.date = vaccine.getDate();
	}

	public VaccineDto() {

	}

	public Vaccine create () {
		return new Vaccine(name, dose, userEmail, date);
	}

	public Long getId () {
		return id;
	}

	public String getName () {
		return name;
	}

	public Integer getDose () {
		return dose;
	}

	public Calendar getDate () {
		return date;
	}

	public String getUserEmail () {
		return userEmail;
	}
}
