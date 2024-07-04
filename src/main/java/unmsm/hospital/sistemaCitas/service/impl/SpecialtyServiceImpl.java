package unmsm.hospital.sistemaCitas.service.impl;

import unmsm.hospital.sistemaCitas.entity.Doctor;
import unmsm.hospital.sistemaCitas.entity.Specialty;
import unmsm.hospital.sistemaCitas.dto.SpecialtyDto;
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

    public SpecialtyServiceImpl(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public void saveSpecialty(SpecialtyDto specialtyDto) {
        Specialty specialty = new Specialty();
        String realname = specialtyDto.getRealname();
        specialty.setRealname(realname);
        specialty.setName(StringUtils.stripAccents(realname).toLowerCase());
        specialty.setDescription(specialtyDto.getDescription());
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

    @Override
    public Specialty findSpecialtyById(Long id){
        return specialtyRepository.getReferenceById(id);
    }

    @Override
    public List<Doctor> listDoctorsBySpecialtyId(Long id){
        return specialtyRepository.getReferenceById(id).getDoctors();
    }

}
