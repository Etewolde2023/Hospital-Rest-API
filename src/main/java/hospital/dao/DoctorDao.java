package hospital.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.entity.Doctor;

public interface DoctorDao extends JpaRepository<Doctor, Long> {

}
