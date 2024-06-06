package unmsm.hospital.sistemaCitas.service.impl;

import unmsm.hospital.sistemaCitas.entity.Patient;
import unmsm.hospital.sistemaCitas.entity.PatientDirectory;
import unmsm.hospital.sistemaCitas.repository.PatientRepository;
import unmsm.hospital.sistemaCitas.repository.PatientDirectoryRepository;
import unmsm.hospital.sistemaCitas.dto.PatientDto;
import unmsm.hospital.sistemaCitas.service.PatientService;

import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class PatientServiceImpl implements PatientService {

	// Asegúrate de inyectar el UserService correctamente
	private final PatientRepository patientRepository;
	private final PatientDirectoryRepository patientDirectoryRepository;

    public PatientServiceImpl
		(PatientRepository patientRepository,
		 PatientDirectoryRepository patientDirectoryRepository) {

		this.patientRepository = patientRepository;
		this.patientDirectoryRepository = patientDirectoryRepository;
    }

    @Override
    public void savePatient(PatientDto patientDto) {

        PatientDirectory patientDirectory = new PatientDirectory();
        patientDirectory.setAddress(patientDto.getAddress());
        patientDirectory.setPhone(patientDto.getPhone());

        Patient patient = new Patient();
        patient.setNames(patientDto.getFirstName());
        patient.setLastnames(patientDto.getLastName());
        patient.setAge(patientDto.getAge());

		//ASOCIACION ENTRE PATIENTDIRECTORY Y PATIENT
		patientDirectory.setPatient(patient);
		// patient.setPatientDirectory(patientDirectory);
		
		patientRepository.save(patient);
		patientDirectoryRepository.save(patientDirectory);
    }

    @Override
    public List<Patient> listPatients(){
		List<Patient> patients = patientRepository.findAll();
		return patients;
    }
}
