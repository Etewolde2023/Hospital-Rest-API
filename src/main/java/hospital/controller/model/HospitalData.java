package hospital.controller.model;

import java.util.HashSet;
import java.util.Set;

import hospital.entity.Doctor;
import hospital.entity.Hospital;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HospitalData {
	
    private Long hospitalId;
	
	private String hospitalName;
	private String hospitalCity;
	private String hospitalAddress;
	private String hopspitalSize;
	private String hospitalEmail;
	private String hospitalPhone;	
	private Set<HospitalDoctor> doctors = new HashSet<>();
	
	public HospitalData(Hospital hospital) {
		hospitalId = hospital.getHospitalId();
		hospitalName = hospital.getHospitalName();
		hospitalCity = hospital.getHospitalCity();
		hospitalAddress = hospital.getHospitalAddress();
		hopspitalSize = hospital.getHopspitalSize();
		hospitalEmail = hospital.getHospitalEmail();
		hospitalPhone = hospital.getHospitalPhone();
		for (Doctor doctor : hospital.getDoctors()) {
			doctors.add(new HospitalDoctor(doctor));
		}
		
	}

}


