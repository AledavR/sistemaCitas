package unmsm.hospital.sistemaCitas.service;

import unmsm.hospital.sistemaCitas.entity.Patient;
import unmsm.hospital.sistemaCitas.dto.PatientDto;

import java.util.List;

public interface PatientService {
        
    void savePatient(PatientDto patientDto);

	List<Patient> listPatients();
    
}

