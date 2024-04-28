package hospital.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;



@Entity
@Data
public class Doctor {	
// identifying the primary key for the doctor table
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long doctorId;
	
	private String doctorFirstName;
	private String doctorLastName;
	private String doctorPhone;
	private String doctorEmail;
	private String doctorSpeciality;
	

	// Many to many relationship with hospital table
		@EqualsAndHashCode.Exclude 
		@ToString.Exclude
		@ManyToMany(mappedBy =  "doctors", cascade = CascadeType.PERSIST)
		private Set<Hospital> hospitals = new HashSet<>();
		
    // One to many relationship with patient table
		@EqualsAndHashCode.Exclude 
		@ToString.Exclude 
		@OneToMany (mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
		private Set<Patient> patients = new HashSet<>();
		
}
