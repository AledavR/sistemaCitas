package unmsm.hospital.sistemaCitas.service;

import unmsm.hospital.sistemaCitas.entity.Doctor;
import unmsm.hospital.sistemaCitas.entity.Specialty;
import unmsm.hospital.sistemaCitas.dto.DoctorDto;

import java.util.List;

public interface DoctorService {
        
    void saveDoctor(DoctorDto doctorDto);

    void updateDoctorSpecialties(Long doctor_id, Long specialty_id);

    List<Doctor> listDoctors();

    Doctor findDoctorById(Long doctor_id);

    List<Specialty> listDoctorSpecialties(Long doctor_id);

}

