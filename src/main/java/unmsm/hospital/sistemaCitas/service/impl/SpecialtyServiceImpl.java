package unmsm.hospital.sistemaCitas.service.impl;

import unmsm.hospital.sistemaCitas.entity.Specialty;
import unmsm.hospital.sistemaCitas.entity.SpecialtyInfo;
import unmsm.hospital.sistemaCitas.dto.SpecialtyDto;
import unmsm.hospital.sistemaCitas.repository.SpecialtyInfoRepository;
import unmsm.hospital.sistemaCitas.repository.SpecialtyRepository;
import unmsm.hospital.sistemaCitas.service.SpecialtyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.apache.commons.lang3.StringUtils;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;
    private SpecialtyInfoRepository specialtyInfoRepository;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository, SpecialtyInfoRepository specialtyInfoRepository) {
        this.specialtyRepository = specialtyRepository;
        this.specialtyInfoRepository = specialtyInfoRepository;
    }

    @Override
    public void saveSpecialty(SpecialtyDto specialtyDto) {
        Specialty specialty = new Specialty();
        SpecialtyInfo specialtyInfo = new SpecialtyInfo();
        String realname = specialtyDto.getRealname();
        specialty.setName(StringUtils.stripAccents(realname).toLowerCase());
        specialtyInfo.setRealname(realname);
        specialtyInfo.setDescription(specialtyDto.getDescription());
        specialtyInfo.setSpecialty(specialty);
        specialtyRepository.save(specialty);
        specialtyInfoRepository.save(specialtyInfo);
    }

    @Override
    public List<Specialty> listSpecialties(){
        List<Specialty> specialties = specialtyRepository.findAll();
        return specialties;
    }
    
    @Override
    public Specialty findSpecialtyByName(String name){
        return specialtyRepository.findByName(name);
    }

}
