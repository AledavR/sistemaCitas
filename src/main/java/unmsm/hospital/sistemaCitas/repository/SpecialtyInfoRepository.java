package unmsm.hospital.sistemaCitas.repository;

import unmsm.hospital.sistemaCitas.entity.SpecialtyInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SpecialtyInfoRepository extends JpaRepository<SpecialtyInfo, Long> {
	
}
