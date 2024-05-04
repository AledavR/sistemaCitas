package unmsm.hospital.sistemaCitas.service;

import unmsm.hospital.sistemaCitas.entity.Specialty;
import unmsm.hospital.sistemaCitas.dto.SpecialtyDto;

import java.util.List;

public interface SpecialtyService {

	void saveSpecialty(SpecialtyDto specialtyDto);
	
	List<Specialty> listSpecialties();

	Specialty findSpecialtyByName(String name);
}
