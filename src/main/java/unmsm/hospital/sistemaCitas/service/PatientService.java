package unmsm.hospital.sistemaCitas.service;

import unmsm.hospital.sistemaCitas.entity.Patient;

import java.util.List;

public interface PatientService {
        
    void savePatient(Long user_id);
    
    boolean patientExists(Long user_id); //Validacion de savePatient en PatientController

    List<Patient> listPatients();
    
    Patient findPatientById(Long id);
    
    void updatePatient(Patient patient);
    
}

