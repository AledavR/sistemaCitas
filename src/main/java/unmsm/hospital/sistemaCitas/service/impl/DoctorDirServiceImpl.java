package unmsm.hospital.sistemaCitas.service.impl;

import org.springframework.stereotype.Service;

import unmsm.hospital.sistemaCitas.entity.DoctorDirectory;
import unmsm.hospital.sistemaCitas.repository.DoctorDirectoryRepository;
import unmsm.hospital.sistemaCitas.service.DoctorDirService;

@Service
public class DoctorDirServiceImpl implements DoctorDirService {

    private final DoctorDirectoryRepository doctorDirectoryRepository;

    public DoctorDirServiceImpl (DoctorDirectoryRepository doctorDirectoryRepository){
        this.doctorDirectoryRepository = doctorDirectoryRepository;
    }


    @Override
    public DoctorDirectory findDoctorDirById(Long id) {
        return doctorDirectoryRepository.getReferenceById(id);
    }

}