package unmsm.hospital.sistemaCitas.service;

import unmsm.hospital.sistemaCitas.entity.Doctor;
import unmsm.hospital.sistemaCitas.dto.DoctorDto;

import java.util.List;

public interface DoctorService {
        
    void saveDoctor(DoctorDto doctorDto);

    void updateDoctor(Long doctor_id, Long specialty_id);

	List<Doctor> listDoctors();
    
}

