package unmsm.hospital.sistemaCitas.entity;

import java.sql.Date;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Table(name = "results")
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String details;

	@Column
	@Temporal(TemporalType.DATE)
	private Date result_date;

	@OneToOne(mappedBy = "result")
	private MedicalService medicalService;

	
}
