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
@Table(name = "doctors")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 *
	 * Esta variable guarda los nombres del doctor
	 *
	 */
	@Column(nullable = false)
	private String names;

	/**
	 *
	 * Esta variable guarda los apellidos del doctor
	 *
	 */
	@Column(nullable = false)
	private String lastnames;

        
	/**
	 *
	 * Definicion de mapeo uno a uno similar a lo visto en las entidades
	 * paciente-directorioPaciente. Llaves primarias compartidas
	 *
	 */
	@OneToOne(mappedBy = "doctor", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private DoctorDirectory doctorDirectory;
	
	/**
	 *
	 * Definicion de muchos a muchos debido a que un doctor puede tener mas
	 * de una especialidad y una especialidad puede ser ejercida por
	 * multiples doctores. Se usa una tabla extra como en el caso de usuario-rol
	 *
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "doctor_specialties",
			   joinColumns =
			   {@JoinColumn(name = "doctor_id", referencedColumnName = "id")},
			   inverseJoinColumns =
			   {@JoinColumn(name = "specialty_id", referencedColumnName = "id")})
	private List<Specialty> specialties = new ArrayList<>();

	@OneToMany(mappedBy = "doctor")
	private Set<MedicalService> medicalServices = new HashSet<>();

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;
        
    }

