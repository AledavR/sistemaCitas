package unmsm.hospital.sistemaCitas.models;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Doctor {
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private String apellido;
	private String especialidad;

	@OneToMany(mappedBy = "doctor")
	private Set<Citas> citas;
	

	public Set<Citas> getCitas() {
		return citas;
	}
	public void setCitas(Set<Citas> citas) {
		this.citas = citas;
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
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
}

