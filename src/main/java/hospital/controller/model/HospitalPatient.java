package hospital.controller.model;

import hospital.entity.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HospitalPatient {
	
    private Long patientId;	
	private String patientFirstName;
	private String patientLastName;
	private String patientGender;
	private String patientDiagnosis;
	private String patientEmailAddress;
	private String patientPhoneNumber;
	
	public HospitalPatient(Patient patient) {
		patientId = patient.getPatientId();
		patientFirstName = patient.getPatientFirstName();
		patientLastName = patient.getPatientLastName();
		patientGender = patient.getPatientGender();
		patientDiagnosis = patient.getPatientDiagnosis();
		patientEmailAddress = patient.getPatientEmailAddress();
		patientPhoneNumber = patient.getPatientPhoneNumber();
	}


}
