package unmsm.hospital.sistemaCitas.service.impl;

import unmsm.hospital.sistemaCitas.entity.Specialty;
import unmsm.hospital.sistemaCitas.dto.SpecialtyDto;
import unmsm.hospital.sistemaCitas.repository.SpecialtyRepository;
import unmsm.hospital.sistemaCitas.service.SpecialtyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyServiceImpl implements SpecialtyService {

    @Autowired
    private SpecialtyRepository specialtyRepository;

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public void saveSpecialty(SpecialtyDto specialtyDto) {
        Specialty specialty = new Specialty();
        specialty.setName(specialtyDto.getName());
        specialtyRepository.save(specialty);
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
