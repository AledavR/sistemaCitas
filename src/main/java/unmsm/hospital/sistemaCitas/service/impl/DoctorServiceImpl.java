package unmsm.hospital.sistemaCitas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unmsm.hospital.sistemaCitas.dto.DoctorDto;
import unmsm.hospital.sistemaCitas.entity.Doctor;
import unmsm.hospital.sistemaCitas.entity.DoctorDirectory;
import unmsm.hospital.sistemaCitas.entity.User;
import unmsm.hospital.sistemaCitas.entity.Specialty;
import unmsm.hospital.sistemaCitas.repository.DoctorRepository;
import unmsm.hospital.sistemaCitas.repository.DoctorDirectoryRepository;
import unmsm.hospital.sistemaCitas.repository.SpecialtyRepository;
import unmsm.hospital.sistemaCitas.service.DoctorService;

import java.util.List;
import java.util.ArrayList;

@Service
public class DoctorServiceImpl implements DoctorService {

    // Asegúrate de inyectar el UserService correctamente
    private final DoctorRepository doctorRepository;
    private final DoctorDirectoryRepository doctorDirectoryRepository;
    private final SpecialtyRepository specialtyRepository;

    @Autowired
    public DoctorServiceImpl
		(DoctorRepository doctorRepository,
		 DoctorDirectoryRepository doctorDirectoryRepository,
		 SpecialtyRepository specialtyRepository) {
		this.doctorRepository = doctorRepository;
		this.doctorDirectoryRepository = doctorDirectoryRepository;
		this.specialtyRepository = specialtyRepository;

    }

    @Override
    public void saveDoctor(DoctorDto doctorDto) {

        DoctorDirectory doctorDirectory = new DoctorDirectory();
        doctorDirectory.setAddress(doctorDto.getAddress());
        doctorDirectory.setPhone(doctorDto.getPhone());

        Doctor doctor = new Doctor();
        doctor.setNames(doctorDto.getFirstName());
        doctor.setLastnames(doctorDto.getLastName());

		ArrayList<Specialty> specialties = new ArrayList<Specialty>();
		specialties.add(specialtyRepository
						.findByName(doctorDto.getSpecialty()));
		doctor.setSpecialties(specialties);
		//ASOCIACION ENTRE DOCTORDIRECTORY Y DOCTOR
		doctorDirectory.setDoctor(doctor);
		// doctor.setDoctorDirectory(doctorDirectory);
		
		doctorRepository.save(doctor);
		doctorDirectoryRepository.save(doctorDirectory);
	}
}
