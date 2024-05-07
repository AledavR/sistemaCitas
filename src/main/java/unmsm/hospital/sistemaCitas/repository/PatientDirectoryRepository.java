package unmsm.hospital.sistemaCitas.repository;

import unmsm.hospital.sistemaCitas.entity.PatientDirectory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientDirectoryRepository extends JpaRepository<PatientDirectory, Long> {
	
}
