package unmsm.hospital.sistemaCitas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Table(name = "patient_directory")
public class PatientDirectory {

	@Id
	@Column(name = "patient_id")
	private Long id;

	@Column(nullable = false)
	private Integer phone;

	@Column(nullable = false)
	private String address;

	@OneToOne
	@MapsId
	@JoinColumn(name = "patient_id")
	private Patient patient;

}
