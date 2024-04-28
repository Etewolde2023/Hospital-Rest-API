package hospital.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Hospital {
// Identifying the primary key for the hospital table
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long hospitalId;
	
	private String hospitalName;
	private String hospitalCity;
	private String hospitalAddress;
	private String hopspitalSize;
	private String hospitalEmail;
	private String hospitalPhone;

	
	// Many to many relationship with doctor table. 
		@EqualsAndHashCode.Exclude 
		@ToString.Exclude
		@ManyToMany (cascade = CascadeType.PERSIST)
			@JoinTable(name = "hospital_doctor", joinColumns = @JoinColumn(name = "hospital_id"), 
				inverseJoinColumns = @JoinColumn(name = "doctor_id"))
		private Set<Doctor> doctors = new HashSet<>();


		
}
