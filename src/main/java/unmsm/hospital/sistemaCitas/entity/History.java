package unmsm.hospital.sistemaCitas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Table(name = "histories")
public class History {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String medical_conditions;

	@Column
	private String operations;

	@Column
	private String allergies;

	@Column
	private String treatments;

	@OneToOne(mappedBy = "history")
	private Patient patient;

	
}
