package unmsm.hospital.sistemaCitas.repositories;

import unmsm.hospital.sistemaCitas.models.Cita;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositorioCita extends JpaRepository<Cita, Long> {
	
}
