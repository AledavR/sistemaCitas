package unmsm.hospital.sistemaCitas.service;

import unmsm.hospital.sistemaCitas.entity.Patient;

import java.util.List;
import unmsm.hospital.sistemaCitas.entity.User;

public interface PatientService {
        
    void savePatient(Long user_id);
    
    boolean patientExists(Long user_id); //Validacion de savePatient en PatientController
    
    User updatePatient(User user);//actualizacion del paciente en patientController

    List<Patient> listPatients();
    
    Patient findPatientById(Long id);
    
    
    
}

