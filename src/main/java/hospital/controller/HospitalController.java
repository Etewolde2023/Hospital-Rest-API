package hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import hospital.controller.model.HospitalData;
import hospital.controller.model.HospitalDoctor;
import hospital.controller.model.HospitalPatient;
import hospital.service.HospitalService;
import lombok.extern.slf4j.Slf4j;





@RestController
@RequestMapping("/hospital")
@Slf4j

public class HospitalController {
	@Autowired
	private HospitalService hospitalService;
	
	@PostMapping("/hospital")
	@ResponseStatus(code = HttpStatus.CREATED)
	public HospitalData saveHospital(@RequestBody HospitalData hospitalData) {
		log.info("creating hospital {}", hospitalData);
		return hospitalService.saveHospital(hospitalData);
	}
	
	@PutMapping("/hospital/{HospitalId}")
	public HospitalData updateHospital(@PathVariable Long HospitalId, 
			@RequestBody HospitalData hospitalData) {
		hospitalData.setHospitalId(HospitalId);
		log.info("Updating hospital {}", hospitalData);
		return hospitalService.saveHospital(hospitalData);

	}	
	

@PostMapping("/{hospitalId}/doctor")
@ResponseStatus(code = HttpStatus.CREATED)
public HospitalDoctor insertDoctor(@PathVariable Long hospitalId, 
		@RequestBody HospitalDoctor hospitalDoctor) {
	log.info("Creating Hospital Doctors {}", hospitalDoctor);
	return hospitalService.saveHospitalDoctor(hospitalId, hospitalDoctor);
}

@PostMapping("/{doctorId}/patient")
@ResponseStatus(code = HttpStatus.CREATED) 
public HospitalPatient insertPatient(@PathVariable Long doctorId, 
		@RequestBody HospitalPatient hospitalPatient) {
	log.info("Creating Patients {}", hospitalPatient);
	return hospitalService.savePatient(doctorId, hospitalPatient);
	
}

@GetMapping("/hospital")
public List<HospitalData> retrieveAllHospitals() {
	log.info("Retrieve All Hospitals That Are Called");
	return hospitalService.retrieveAllHospitals();
	
}

@GetMapping("/hospital/{hospitalId}")
public HospitalData retrieveHospitalById(@PathVariable Long hospitalId) {
	log.info("Retrieving Hospital with ID={}", hospitalId);
	return hospitalService.retrieveHospitalById(hospitalId);
	
}

@DeleteMapping("/hospital/{hospitalId}")
public void deleteHospitalById(@PathVariable Long hospitalId) {
	log.info("Deleting Hospital With ID={}", hospitalId);
	hospitalService.deleteHospitalById(hospitalId);
}


}
