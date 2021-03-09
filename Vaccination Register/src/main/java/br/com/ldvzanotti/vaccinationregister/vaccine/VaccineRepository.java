package br.com.ldvzanotti.vaccinationregister.vaccine;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {
}
