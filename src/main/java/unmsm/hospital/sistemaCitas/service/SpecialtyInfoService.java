package unmsm.hospital.sistemaCitas.service;

import java.util.List;

import unmsm.hospital.sistemaCitas.entity.Specialty;
import unmsm.hospital.sistemaCitas.entity.SpecialtyInfo;

public interface SpecialtyInfoService {

    SpecialtyInfo findSpecialtyInfoById(Long specialty_id);
    
    List<SpecialtyInfo> listSpecialtiesInfo();
}
