package unmsm.hospital.sistemaCitas.service;

import unmsm.hospital.sistemaCitas.entity.Patient;

import java.util.List;

public interface PatientService {
        
    void savePatient(Long user_id);

    List<Patient> listPatients();
    
}

