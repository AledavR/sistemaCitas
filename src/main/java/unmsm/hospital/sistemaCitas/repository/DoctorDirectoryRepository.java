package unmsm.hospital.sistemaCitas.repository;

import unmsm.hospital.sistemaCitas.entity.DoctorDirectory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DoctorDirectoryRepository extends JpaRepository<DoctorDirectory, Long> {
    
}
