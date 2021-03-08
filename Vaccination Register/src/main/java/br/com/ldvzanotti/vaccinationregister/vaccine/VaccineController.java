package br.com.ldvzanotti.vaccinationregister.vaccine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/vaccines")
public class VaccineController {

	@Autowired
	VaccineRepository vaccineRepository;

	@PostMapping("/register")
	@Transactional
	public ResponseEntity<VaccineDto> register
			(@RequestBody @Valid VaccineDto vaccineDto, UriComponentsBuilder uriBuilder) {
		Vaccine vaccine = vaccineDto.create();
		vaccineRepository.save(vaccine);
		URI uri = uriBuilder.path("/vaccines/{id}").buildAndExpand(vaccine.getId()).toUri();
		return ResponseEntity.created(uri).body(new VaccineDto(vaccine));
	}
}