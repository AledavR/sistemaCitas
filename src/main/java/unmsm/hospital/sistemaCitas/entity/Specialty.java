package unmsm.hospital.sistemaCitas.entity;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Table(name = "specialties")
public class Specialty {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String name;

	@ManyToMany(mappedBy = "specialties")
	private List<Doctor> doctors = new ArrayList<>();

	@OneToMany(mappedBy = "specialty")
	private Set<MedicalService> medicalServices = new HashSet<>();
	
}
