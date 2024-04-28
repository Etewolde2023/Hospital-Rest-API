package hospital.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Patient {
// Identifying the primary key for the patient table
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long patientId;
	
	private String patientFirstName;
	private String patientLastName;
	private String patientGender;
	private String patientDiagnosis;
	private String patientEmailAddress;
	private String patientPhoneNumber;

// Many to one relationship with doctor table
	@EqualsAndHashCode.Exclude 
	@ToString.Exclude
	@ManyToOne (cascade = CascadeType.ALL)
	// joins tables by doctor_id
	@JoinColumn(name = "doctor_id")
	private Doctor doctor; 
	
}
