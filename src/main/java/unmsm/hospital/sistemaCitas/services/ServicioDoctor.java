package unmsm.hospital.sistemaCitas.services;

import unmsm.hospital.sistemaCitas.models.Doctor;
import unmsm.hospital.sistemaCitas.repositories.RepositorioDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioDoctor {
	@Autowired
	private RepositorioDoctor repositorioDoctor;

	public List<Doctor> list() {
		return repositorioDoctor.findAll();
	}

	public Doctor save(Doctor doctor){
		return repositorioDoctor.save(doctor);
	}
	
	/**
	 *
	 * Permite conseguir el nombre de un doctor a traves del id.
	 *
	 * @param id ID del doctor
	 * @return El objeto doctor con ID elegido
	 *
	 */
	public Doctor get(Long id){
		Optional<Doctor> doctorOpcional = repositorioDoctor.findById(id);
		if (doctorOpcional.isEmpty()) {
			throw new
				ResponseStatusException(org. springframework.http.HttpStatus.NOT_FOUND,
						"Doctor no encontrado");
		}
		return doctorOpcional.get();
	}

	public Doctor update(Long id, Doctor doctor){
		Optional<Doctor> doctorOpcional = repositorioDoctor.findById(id);
		if (doctorOpcional.isEmpty()) {
			throw new
				ResponseStatusException(org. springframework.http.HttpStatus.NOT_FOUND,
						"Doctor no encontrado");
		}
		Doctor doctorExistente = doctorOpcional.get();
		doctorExistente.setNombre(doctor.getNombre());
		return repositorioDoctor.save(doctorExistente);
	}

	public void delete(Long id){
		repositorioDoctor.deleteById(id);
	}

}
