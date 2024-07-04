package unmsm.hospital.sistemaCitas.service;

import unmsm.hospital.sistemaCitas.dto.MedicalServiceDto;
import unmsm.hospital.sistemaCitas.entity.MedicalServiceType;

import java.util.List;

public interface MedicalServService {

    void saveMedicalService(MedicalServiceDto medicalServiceDto, Long patientId);

    List<MedicalServiceType> listMedicalServiceTypes();

}
