package hospital.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import hospital.entity.Hospital;

public interface HospitalDao extends JpaRepository<Hospital, Long> {


}
