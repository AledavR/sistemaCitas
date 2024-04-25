package unmsm.hospital.sistemaCitas.repositories;

import unmsm.hospital.sistemaCitas.models.Paciente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPaciente extends JpaRepository<Paciente, Long>, JpaSpecificationExecutor<Paciente> {

	/**
	 * Este metodo permite encontrar un paciente en la base de
	 * datos usando su nombre.
	 * 
	 * @since 2024-04-20
	 */
	Optional<Paciente> findByNombre(String nombre);
	
}
