package unmsm.hospital.sistemaCitas.service;

import unmsm.hospital.sistemaCitas.entity.DoctorDirectory;

public interface DoctorDirService {

    DoctorDirectory findDoctorDirById(Long id);
    
}
