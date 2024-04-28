package hospital.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.entity.Patient;

public interface PatientDao extends JpaRepository<Patient, Long> {

}
