package unmsm.hospital.sistemaCitas.service.impl;

import unmsm.hospital.sistemaCitas.entity.Doctor;
import unmsm.hospital.sistemaCitas.entity.DoctorDirectory;
import unmsm.hospital.sistemaCitas.entity.Specialty;
import unmsm.hospital.sistemaCitas.repository.DoctorRepository;
import unmsm.hospital.sistemaCitas.repository.DoctorDirectoryRepository;
import unmsm.hospital.sistemaCitas.repository.SpecialtyRepository;
import unmsm.hospital.sistemaCitas.dto.DoctorDto;
import unmsm.hospital.sistemaCitas.service.DoctorService;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DoctorServiceImpl implements DoctorService {

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
    
    @Override
    public void updateDoctorSpecialties(Long doctor_id, Long specialty_id) {

        Doctor existing_doctor = doctorRepository.getReferenceById(doctor_id);
	Specialty new_specialty = specialtyRepository.getReferenceById(specialty_id);
	
	ArrayList<Specialty> specialties = new ArrayList<Specialty>(existing_doctor.getSpecialties());
	specialties.add(new_specialty);
	existing_doctor.setSpecialties(specialties);
	
	doctorRepository.save(existing_doctor);
    }
    
    @Override
    public Doctor findDoctorById(Long doctor_id){
	return doctorRepository.getReferenceById(doctor_id);
    }

    @Override
    public List<Doctor> listDoctors(){
	List<Doctor> doctors = doctorRepository.findAll();
	return doctors;
    }

    @Override
    public List<Specialty> listDoctorSpecialties(Long doctor_id){
	Doctor doctor = doctorRepository.getById(doctor_id);
	List<Specialty> specialties = doctor.getSpecialties();
	return specialties;
    }
}
