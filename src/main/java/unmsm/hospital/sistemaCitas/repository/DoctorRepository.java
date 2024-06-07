package unmsm.hospital.sistemaCitas.repository;

import unmsm.hospital.sistemaCitas.entity.Doctor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    
}
