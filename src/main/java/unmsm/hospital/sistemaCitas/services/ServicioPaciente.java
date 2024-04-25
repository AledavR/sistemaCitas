package unmsm.hospital.sistemaCitas.services;

import unmsm.hospital.sistemaCitas.models.Paciente;
import unmsm.hospital.sistemaCitas.repositories.RepositorioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioPaciente {
	@Autowired
	private RepositorioPaciente repositorioPaciente;

	public List<Paciente> list() {
		return repositorioPaciente.findAll();
	}

	public Paciente save(Paciente paciente){
		return repositorioPaciente.save(paciente);
	}
	
	/**
	 *
	 * Permite conseguir el nombre de un paciente a traves del id.
	 *
	 * @param id ID del paciente
	 * @return El objeto paciente con ID elegido
	 *
	 */
	public Paciente get(Long id){
		Optional<Paciente> pacienteOpcional = repositorioPaciente.findById(id);
		if (pacienteOpcional.isEmpty()) {
			throw new
				ResponseStatusException(org. springframework.http.HttpStatus.NOT_FOUND,
						"Paciente no encontrado");
		}
		return pacienteOpcional.get();
	}

	public Paciente update(Long id, Paciente paciente){
		Optional<Paciente> pacienteOpcional = repositorioPaciente.findById(id);
		if (pacienteOpcional.isEmpty()) {
			throw new
				ResponseStatusException(org. springframework.http.HttpStatus.NOT_FOUND,
						"Paciente no encontrado");
		}
		Paciente pacienteExistente = pacienteOpcional.get();
		pacienteExistente.setNombre(paciente.getNombre());
		return repositorioPaciente.save(pacienteExistente);
	}

	public void delete(Long id){
		repositorioPaciente.deleteById(id);
	}

	public Paciente getByNombre(String nombre){
		Optional<Paciente> pacienteOpcional = repositorioPaciente.findByNombre(nombre);
		if (pacienteOpcional.isEmpty()) {
			throw new
				ResponseStatusException(org. springframework.http.HttpStatus.NOT_FOUND,
						"Paciente no encontrado");
		}
		return pacienteOpcional.get();
		
	}

}
