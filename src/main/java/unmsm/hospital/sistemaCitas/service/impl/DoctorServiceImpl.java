package unmsm.hospital.sistemaCitas.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import unmsm.hospital.sistemaCitas.entity.Doctor;
import unmsm.hospital.sistemaCitas.entity.Specialty;
import unmsm.hospital.sistemaCitas.entity.User;
import unmsm.hospital.sistemaCitas.repository.DoctorRepository;
import unmsm.hospital.sistemaCitas.repository.SpecialtyRepository;
import unmsm.hospital.sistemaCitas.repository.UserRepository;
import unmsm.hospital.sistemaCitas.service.DoctorService;


@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final SpecialtyRepository specialtyRepository;
    private final UserRepository userRepository;

    public DoctorServiceImpl
        (DoctorRepository doctorRepository,
         SpecialtyRepository specialtyRepository,
         UserRepository userRepository) {
        
        this.doctorRepository = doctorRepository;
        this.specialtyRepository = specialtyRepository;
        this.userRepository = userRepository;
        
    }

    @Override
    public void saveDoctor(Long user_id, Long specialty_id) {
        
        Doctor doctor = new Doctor();
        User user = userRepository.getReferenceById(user_id);
        Specialty specialty = specialtyRepository.getReferenceById(specialty_id); 

        ArrayList<Specialty> specialties = new ArrayList<Specialty>();
        specialties.add(specialty);
        doctor.setSpecialties(specialties);

        doctor.setUser(user);
        doctorRepository.save(doctor);
        
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
        Doctor doctor = doctorRepository.getReferenceById(doctor_id);
        List<Specialty> specialties = doctor.getSpecialties();
        return specialties;
    }

    @Override
    public void associateUserWithDoctor(Long doctor_id, Long user_id){
        Doctor doctor = doctorRepository.getReferenceById(doctor_id);
        User user = userRepository.getReferenceById(user_id);

        doctor.setUser(user);
        doctorRepository.save(doctor);
    }
    
}
