package hospital.controller.model;

import hospital.entity.Doctor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class HospitalDoctor {
	
    private Long doctorId;	
	private String doctorFirstName;
	private String doctorLastName;
	private String doctorPhone;
	private String doctorEmail;
	private String doctorSpeciality;
	
	
   public HospitalDoctor(Doctor doctor) {
	   doctorId = doctor.getDoctorId();
	   doctorFirstName = doctor.getDoctorFirstName();
	   doctorLastName = doctor.getDoctorLastName();
	   doctorPhone = doctor.getDoctorPhone();
	   doctorEmail = doctor.getDoctorEmail();
	   doctorSpeciality = doctor.getDoctorSpeciality();
	   
   }
}


