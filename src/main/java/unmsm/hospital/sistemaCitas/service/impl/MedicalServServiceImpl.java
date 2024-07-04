package unmsm.hospital.sistemaCitas.service.impl;

import unmsm.hospital.sistemaCitas.dto.MedicalServiceDto;
import unmsm.hospital.sistemaCitas.entity.MedicalService;
import unmsm.hospital.sistemaCitas.entity.MedicalServiceType;
import unmsm.hospital.sistemaCitas.entity.Patient;
import unmsm.hospital.sistemaCitas.entity.User;
import unmsm.hospital.sistemaCitas.entity.Doctor;
import unmsm.hospital.sistemaCitas.repository.DoctorRepository;
import unmsm.hospital.sistemaCitas.repository.MedicalServiceRepository;
import unmsm.hospital.sistemaCitas.repository.MedicalServiceTypeRepository;
import unmsm.hospital.sistemaCitas.repository.PatientRepository;
import unmsm.hospital.sistemaCitas.repository.SpecialtyRepository;
import unmsm.hospital.sistemaCitas.repository.UserRepository;
import unmsm.hospital.sistemaCitas.service.MedicalServService;
import unmsm.hospital.sistemaCitas.service.PatientService;

import java.sql.Date;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MedicalServServiceImpl implements MedicalServService {

   
    private PatientRepository patientRepository;
    private UserRepository userRepository;
    private DoctorRepository doctorRepository;
    private MedicalServiceRepository medicalServiceRepository;
    private SpecialtyRepository specialtyRepository;
    private MedicalServiceTypeRepository medicalServiceTypeRepository;

    public MedicalServServiceImpl(
        PatientRepository patientRepository,
        UserRepository userRepository,
        DoctorRepository doctorRepository,
        MedicalServiceRepository medicalServiceRepository,
        MedicalServiceTypeRepository medicalServiceTypeRepository,
        SpecialtyRepository specialtyRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
        this.doctorRepository = doctorRepository;
        this.medicalServiceRepository = medicalServiceRepository;
        this.medicalServiceTypeRepository = medicalServiceTypeRepository;
        this.specialtyRepository = specialtyRepository;
    }

    @Override
    public void saveMedicalService(MedicalServiceDto medicalServiceDto, Long patientId) {
        MedicalService medicalService = new MedicalService();

        Doctor doctor = doctorRepository.getReferenceById(medicalServiceDto.getDoctor_id());
        Patient patient = patientRepository.getReferenceById(patientId);
        
        medicalService.setDetails("Detalle por defecto");
        medicalService.setDoctor(doctor);
        medicalService.setPatient(patient);
        medicalService.setService_date(medicalServiceDto.getService_date());
        medicalService.setSpecialty(specialtyRepository.getReferenceById(medicalServiceDto.getSpecialty_id()));
        medicalService.setMedicalServiceType(medicalServiceTypeRepository.getReferenceById(medicalServiceDto.getType_id()));

        java.util.Date date = new java.util.Date();
        medicalService.setCreation_date(new java.sql.Date(date.getTime()));
        
        medicalServiceRepository.save(medicalService);

    }

    @Override
    public List<MedicalServiceType> listMedicalServiceTypes(){
        return medicalServiceTypeRepository.findAll();
    }

    @Override
    public List<MedicalService> listMedicalServicesByPatient(Patient patient){
        return medicalServiceRepository.findByPatient(patient);
    }
    
}
