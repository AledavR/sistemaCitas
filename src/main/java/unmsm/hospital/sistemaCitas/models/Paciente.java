package unmsm.hospital.sistemaCitas.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Paciente {
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private String apellido;
	private Integer edad;

	public Paciente() {
	}

	public Paciente(String nombre,
					String apellido,
					Integer edad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
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
