package unmsm.hospital.sistemaCitas.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import unmsm.hospital.sistemaCitas.entity.SpecialtyInfo;
import unmsm.hospital.sistemaCitas.repository.SpecialtyInfoRepository;
import unmsm.hospital.sistemaCitas.service.SpecialtyInfoService;

@Service
public class SpecialtyInfoServiceImpl implements SpecialtyInfoService {
    
    private final SpecialtyInfoRepository specialtyInfoRepository;

    public SpecialtyInfoServiceImpl (SpecialtyInfoRepository specialtyInfoRepository) {
	    this.specialtyInfoRepository = specialtyInfoRepository;
    }

    @Override
    public SpecialtyInfo findSpecialtyInfoById(Long specialty_id){
        return specialtyInfoRepository.getReferenceById(specialty_id);
    }

    @Override
    public List<SpecialtyInfo> listSpecialtiesInfo(){
        List<SpecialtyInfo> specialtiesInfo = specialtyInfoRepository.findAll();
        return specialtiesInfo;
    }
}
