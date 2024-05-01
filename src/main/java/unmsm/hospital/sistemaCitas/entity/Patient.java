package unmsm.hospital.sistemaCitas.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 *
 * Entidad basica de paciente. Posee los atributos
 * names, lastnames y age
 * <p>
 * Presenta relaciones con las entidades PatientDirectory,
 * History, MedicalService y User 
 * @author Alejandro Ramirez
 *
 */
@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Table(name = "patients")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String names;

	@Column(nullable = false)
	private String lastnames;

	@Column(nullable = false)
	private Integer age;

	/**
	 *
	 * Definicion de la relacion uno a uno de la tabla paciente con la tabla
	 * directorio_paciente, en este caso se usa el metodo de compartir una llave
	 * primaria entre las tablas la cual es mapeada por el paciente. Es importante
	 * indicar que este tipo de relacion conlleva a que sea necesario definir un
	 * directorio de paciente por cada paciente. (NO PUEDE SER NULO...creo)
	 *
	 */
	@OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private PatientDirectory patientDirectory;

	/**
	 * 
	 * Definicion de una relacion de uno a uno usando una tabla relacional extra.
	 * En este caso la tabla que las relaciona se llama "patient_history" la cual
	 * posee como columnas a "patient_id" y "history_id" las cuales relacionan
	 * las id de ambas tablas. Este metodo permite que el paciente no tenga una
	 * historia previa por lo que podemos evitar los valores NULL en las tablas.
	 *
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "patient_history",
			   joinColumns =
			   {@JoinColumn(name = "patient_id", referencedColumnName = "id")},
			   inverseJoinColumns =
			   {@JoinColumn(name = "history_id", referencedColumnName = "id")})
	private History history;

	@OneToMany(mappedBy = "patient")
	private List<MedicalService> medicalServices;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
