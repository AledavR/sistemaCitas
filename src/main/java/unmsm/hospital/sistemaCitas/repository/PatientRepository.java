package unmsm.hospital.sistemaCitas.repository;

import unmsm.hospital.sistemaCitas.entity.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
	
}
