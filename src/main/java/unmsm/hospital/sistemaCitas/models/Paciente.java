package unmsm.hospital.sistemaCitas.models;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Paciente {
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private String apellido;
	private Integer edad;

	@OneToMany(mappedBy = "paciente")
	private Set<Cita> cita;

	public Set<Cita> getCita() {
		return cita;
	}

	public void setCita(Set<Cita> cita) {
		this.cita = cita;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}


	@Override
	public String toString() {
		return "Paciente [id=" + id +
				", nombre=" + nombre +
				", apellido=" + apellido +
				", edad=" + edad + "]";
	}
}
