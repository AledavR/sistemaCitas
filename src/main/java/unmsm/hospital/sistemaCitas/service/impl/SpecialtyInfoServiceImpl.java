package unmsm.hospital.sistemaCitas.service.impl;

import unmsm.hospital.sistemaCitas.entity.SpecialtyInfo;
import unmsm.hospital.sistemaCitas.repository.SpecialtyInfoRepository;
import unmsm.hospital.sistemaCitas.service.SpecialtyInfoService;

public class SpecialtyInfoServiceImpl implements SpecialtyInfoService {
    
    private final SpecialtyInfoRepository specialtyInfoRepository;

    public SpecialtyInfoServiceImpl (SpecialtyInfoRepository specialtyInfoRepository) {
	
	    this.specialtyInfoRepository = specialtyInfoRepository;
	
    }

    @Override
    public SpecialtyInfo findSpecialtyInfoById(Long specialty_id){
	    return specialtyInfoRepository.getReferenceById(specialty_id);
    }
}
