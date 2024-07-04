package unmsm.hospital.sistemaCitas.service;

import unmsm.hospital.sistemaCitas.dto.MedicalServiceDto;
import unmsm.hospital.sistemaCitas.entity.MedicalService;
import unmsm.hospital.sistemaCitas.entity.MedicalServiceType;
import unmsm.hospital.sistemaCitas.entity.Patient;

import java.util.List;

public interface MedicalServService {

    void saveMedicalService(MedicalServiceDto medicalServiceDto, Long patientId);

    List<MedicalServiceType> listMedicalServiceTypes();

    List<MedicalService> listMedicalServicesByPatient(Patient patient);

}
