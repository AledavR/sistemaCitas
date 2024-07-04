package unmsm.hospital.sistemaCitas.repository;

import unmsm.hospital.sistemaCitas.entity.MedicalService;
import unmsm.hospital.sistemaCitas.entity.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;



@Repository
public interface MedicalServiceRepository extends JpaRepository<MedicalService, Long> {
    List<MedicalService> findByPatient(Patient patient);
}
