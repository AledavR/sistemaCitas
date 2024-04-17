package unmsm.hospital.sistemaCitas.repositories;

import unmsm.hospital.sistemaCitas.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioPaciente extends JpaRepository<Paciente, Long> {
	
}
