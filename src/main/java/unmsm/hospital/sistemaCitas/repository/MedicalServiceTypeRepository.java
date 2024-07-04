package unmsm.hospital.sistemaCitas.repository;

import unmsm.hospital.sistemaCitas.entity.MedicalServiceType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MedicalServiceTypeRepository extends JpaRepository<MedicalServiceType, Long> {
    
}
