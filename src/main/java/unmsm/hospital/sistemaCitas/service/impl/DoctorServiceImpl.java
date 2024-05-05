package unmsm.hospital.sistemaCitas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unmsm.hospital.sistemaCitas.dto.DoctorDto;
import unmsm.hospital.sistemaCitas.entity.Doctor;
import unmsm.hospital.sistemaCitas.entity.DoctorDirectory;
import unmsm.hospital.sistemaCitas.entity.User;
import unmsm.hospital.sistemaCitas.repository.DoctorRepository;
import unmsm.hospital.sistemaCitas.service.DoctorService;
import unmsm.hospital.sistemaCitas.service.UserService;

@Service
public class DoctorServiceImpl implements DoctorService {

    // Asegúrate de inyectar el UserService correctamente
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;

    }

    @Override
    public void saveDoctor(DoctorDto doctorDto) {

        DoctorDirectory doctorDirectory = new DoctorDirectory();
        doctorDirectory.setAddress(doctorDto.getAddress());
        doctorDirectory.setPhone(doctorDto.getPhone());

        Doctor doctor = new Doctor();
        doctor.setNames(doctorDto.getFirstName());
        doctor.setLastnames(doctorDto.getLastName());
        doctor.setSpecialty(doctorDto.getSpecialty());

        //ASOCIACION ENTRE DOCTORDIRECTORY Y DOCTOR
        doctorDirectory.setDoctor(doctor);
        doctor.setDoctorDirectory(doctorDirectory);
        
        doctorRepository.save(doctor);
    }
}
