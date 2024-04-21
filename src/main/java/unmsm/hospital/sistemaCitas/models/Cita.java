
package unmsm.hospital.sistemaCitas.models;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Cita {
	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name="id_paciente",nullable=false)
	private Paciente paciente;

	@ManyToOne
	@JoinColumn(name="id_doctor",nullable=false)
	private Doctor doctor;

	@Temporal(TemporalType.DATE)
	private Date fecha_cita;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Date getFecha_cita() {
		return fecha_cita;
	}

	public void setFecha_cita(Date fecha_cita) {
		this.fecha_cita = fecha_cita;
	}
	
}
