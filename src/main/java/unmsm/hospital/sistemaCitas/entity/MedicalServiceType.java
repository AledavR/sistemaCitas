package unmsm.hospital.sistemaCitas.entity;

import java.util.List;
import java.sql.Date;
import java.util.ArrayList;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Table(name = "medical_service_types")
public class MedicalServiceType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String type;

	@OneToMany(mappedBy = "medicalServiceType")
	private List<MedicalService> medicalServices;

}
