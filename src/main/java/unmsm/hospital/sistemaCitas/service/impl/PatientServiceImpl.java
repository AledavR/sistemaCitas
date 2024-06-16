package unmsm.hospital.sistemaCitas.service.impl;

import unmsm.hospital.sistemaCitas.entity.Patient;
import unmsm.hospital.sistemaCitas.entity.User;
import unmsm.hospital.sistemaCitas.repository.PatientRepository;
import unmsm.hospital.sistemaCitas.repository.UserRepository;
import unmsm.hospital.sistemaCitas.service.PatientService;

import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

    // Asegúrate de inyectar el UserService correctamente
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientServiceImpl(PatientRepository patientRepository,
            UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void savePatient(Long user_id) {

        Patient patient = new Patient();
        User user = userRepository.getReferenceById(user_id);

        patient.setUser(user);
        patientRepository.save(patient);

    }

    @Override
    public List<Patient> listPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients;
    }

    @Override
    public Patient findPatientById(Long patient_id) {
        return patientRepository.getReferenceById(patient_id);
    }

    @Override
    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
        }
    }


