package hospital.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hospital.controller.model.HospitalData;
import hospital.controller.model.HospitalDoctor;
import hospital.controller.model.HospitalPatient;
import hospital.dao.DoctorDao;
import hospital.dao.HospitalDao;
import hospital.dao.PatientDao;
import hospital.entity.Doctor;
import hospital.entity.Hospital;
import hospital.entity.Patient;



@Service
public class HospitalService {	

	
@Autowired
private HospitalDao hospitalDao;


@Transactional (readOnly = false)
public HospitalData saveHospital(HospitalData hospitalData) {	
	
	Long hospitalId = hospitalData.getHospitalId();
	Hospital hospital = findOrCreateHospital(hospitalId);
	copyHospitalFields(hospital, hospitalData);
	return new HospitalData(hospitalDao.save(hospital));	
	
}

private void copyHospitalFields(Hospital hospital, HospitalData hospitalData) {
	hospital.setHospitalId(hospitalData.getHospitalId());
	hospital.setHospitalName(hospitalData.getHospitalName());
	hospital.setHospitalCity(hospitalData.getHospitalCity());
	hospital.setHospitalAddress(hospitalData.getHospitalAddress());
	hospital.setHopspitalSize(hospitalData.getHopspitalSize());
	hospital.setHospitalEmail(hospitalData.getHospitalEmail());
	hospital.setHospitalPhone(hospitalData.getHospitalPhone());	
	
}

private Hospital findOrCreateHospital(Long hospitalId) {
	if(Objects.isNull(hospitalId)) {
		 return new Hospital();
		} 
	     else {
			return findHospitalById(hospitalId);
	     }
		
	
	
}

private Hospital findHospitalById(Long hospitalId) {	
		
		return hospitalDao.findById(hospitalId)
				.orElseThrow(() -> new NoSuchElementException(
						"Hospital with Id " + hospitalId + " was not found"));

}

@Autowired
private DoctorDao doctorDao;

@Transactional(readOnly = false)
public HospitalDoctor saveHospitalDoctor(Long hospitalId, HospitalDoctor hospitalDoctor) {	
	Hospital hospital = findHospitalById (hospitalId);
	Long doctorId = hospitalDoctor.getDoctorId();
	Doctor doctor = findOrCreateDoctor(hospitalId, doctorId);
	copyDoctorFields(doctor, hospitalDoctor);
	
	
	// Add hospital to Set in Doctor
	doctor.getHospitals().add(hospital);
		
			
	// Add doctor to Set in Hospital. 
	hospital.getDoctors().add(doctor);
	
	return new HospitalDoctor(doctorDao.save(doctor));	

}

private void copyDoctorFields(Doctor doctor, HospitalDoctor hospitalDoctor) {
	doctor.setDoctorId(hospitalDoctor.getDoctorId());
	doctor.setDoctorFirstName(hospitalDoctor.getDoctorFirstName());
	doctor.setDoctorLastName(hospitalDoctor.getDoctorLastName());
	doctor.setDoctorPhone(hospitalDoctor.getDoctorPhone());
	doctor.setDoctorEmail(hospitalDoctor.getDoctorEmail());
	doctor.setDoctorSpeciality(hospitalDoctor.getDoctorSpeciality());
	
		
	
}

private Doctor findOrCreateDoctor(Long hospitalId, Long doctorId) {
	Doctor doctor;
	
	if(Objects.isNull(doctorId)) {
		 doctor = new Doctor();
		} 
	     else {
			doctor = findDoctorById(hospitalId, doctorId);
}
	return doctor;
	
}
private Doctor findDoctorById(Long hospitalId, Long doctorId) {
	Doctor doctor = doctorDao.findById(doctorId)
			.orElseThrow(() -> new NoSuchElementException("doctor with ID= " + doctorId
					+ " was not found."));
	
			boolean found = false;
			
			for(Hospital hospital : doctor.getHospitals()) {
				if(hospital.getHospitalId() == hospitalId) {
					found = true;
					break;
					
				}
			}
			if(!found) {
				throw new IllegalArgumentException("Doctor with ID= " + doctorId +
						"does not work at Hospital with ID= " + hospitalId + ".");
	
	}
			return doctor;

	}
@Autowired
private PatientDao patientDao;

@Transactional(readOnly = false)
public HospitalPatient savePatient(Long doctorId, HospitalPatient hospitalPatient) {
	Doctor doctor = findDoctorById(doctorId);
	Long patientId = hospitalPatient.getPatientId();
	Patient patient = findOrCreatePatient(doctorId, patientId);
	copyPatientFields(patient, hospitalPatient);
	patient.setDoctor(doctor);
	doctor.getPatients().add(patient);
	
	return new HospitalPatient(patientDao.save(patient));
}


private Doctor findDoctorById(Long doctorId) {	
	Doctor doctor = doctorDao.findById(doctorId)
			.orElseThrow(() -> new NoSuchElementException("doctor with ID= " + doctorId
					+ " was not found."));
	return doctor;
}

private void copyPatientFields(Patient patient, HospitalPatient hospitalPatient) {
	patient.setPatientFirstName(hospitalPatient.getPatientFirstName());
	patient.setPatientLastName(hospitalPatient.getPatientLastName());
	patient.setPatientGender(hospitalPatient.getPatientGender());
	patient.setPatientDiagnosis(hospitalPatient.getPatientDiagnosis());
	patient.setPatientEmailAddress(hospitalPatient.getPatientEmailAddress());
	patient.setPatientPhoneNumber(hospitalPatient.getPatientPhoneNumber());
}

private Patient findOrCreatePatient(Long doctorId, Long patientId) {
	Patient patient;
	
	if(Objects.isNull(patientId)) {
		patient = new Patient();
	}
	else {
		patient = findPatientById(doctorId, patientId);
		
	}
	return patient;
}

private Patient findPatientById(Long doctorId, Long patientId) {
	Patient patient = patientDao.findById(patientId)
			.orElseThrow(() -> new NoSuchElementException("Patient with ID= " + patientId
			+ " was not found."));
			
		return patient;
}


@Transactional(readOnly = true)
public List<HospitalData> retrieveAllHospitals() {
	List<Hospital> hospitals = hospitalDao.findAll();
	List<HospitalData> solution = new LinkedList<>();
	for (Hospital hospital : hospitals) {
		HospitalData hd = new HospitalData(hospital);
		hd.getDoctors().clear();
		solution.add(hd);		
	}
	return solution;

}

public HospitalData retrieveHospitalById(Long hospitalId) {	
   
	Hospital hospital = findHospitalById(hospitalId);
	return new HospitalData(hospital);

    

}
@Transactional(readOnly = false)
public void deleteHospitalById(Long hospitalId) {
	Hospital hospital = findHospitalById(hospitalId);
	hospitalDao.delete(hospital);
}


}


	
	
	







