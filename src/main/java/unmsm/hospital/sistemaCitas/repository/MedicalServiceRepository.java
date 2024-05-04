package unmsm.hospital.sistemaCitas.repository;

import unmsm.hospital.sistemaCitas.entity.MedicalService;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long> {
	
}
