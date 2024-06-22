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
        patientRepository.save(patient);//ver PatientRepostory, dónde está el save?

    }

    @Override
    public boolean patientExists(Long userId) { //verificamos si ya existe el paciente 
        //podría usar algo semejante a savePatient?
        return patientRepository.existsByUserId(userId);
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
    public User updatePatient(User user) {
        
        // Obtener el usuario existente por su ID
        User existingUser = userRepository.findById(user.getId()).orElse(null);

        // Actualizar los campos necesarios
        existingUser.setNames(user.getNames());
        existingUser.setLastnames(user.getLastnames());
        existingUser.setPhone(user.getPhone());

        // Guardar los cambios en la base de datos
        return userRepository.save(existingUser); /*No puedo usar el PatientRepository, no es mala idea
                                                    un repositorio genérico que pueda manejar
                                                    diferentes tipos de entidades como User,Long y Patient,Long
                                                    a la vez. 
                                                    */
    }
}
