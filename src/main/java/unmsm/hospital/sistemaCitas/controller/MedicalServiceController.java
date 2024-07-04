package unmsm.hospital.sistemaCitas.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import jakarta.validation.Valid;
import unmsm.hospital.sistemaCitas.dto.MedicalServiceDto;
import unmsm.hospital.sistemaCitas.repository.PatientRepository;
import unmsm.hospital.sistemaCitas.service.DoctorService;
import unmsm.hospital.sistemaCitas.service.MedicalServService;
import unmsm.hospital.sistemaCitas.service.PatientService;
import unmsm.hospital.sistemaCitas.service.SpecialtyService;
import unmsm.hospital.sistemaCitas.service.UserService;

@Controller
public class MedicalServiceController {

    private final SpecialtyService specialtyService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final UserService userService;
    private final MedicalServService medicalServService;

    public MedicalServiceController(
        SpecialtyService specialtyService,
        PatientService patientService,
        DoctorService doctorService,
        UserService userService,
        MedicalServService medicalServService
    ){
        this.specialtyService = specialtyService;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.userService = userService;
        this.medicalServService = medicalServService;
    }

    @GetMapping("/service/service-options")
    public String showServiceOptions(Model model){
        model.addAttribute("specialties", specialtyService.listSpecialties());
        return "service/service-options";
    }

    @GetMapping("/service/saveService")
    public String showServiceForm(
        @RequestParam Long specialty_id,
        Model model
    ){
        model.addAttribute("specialty", specialtyService.findSpecialtyById(specialty_id));
        model.addAttribute("doctors", specialtyService.listDoctorsBySpecialtyId(specialty_id));
        model.addAttribute("types", medicalServService.listMedicalServiceTypes());
        model.addAttribute("medicalService", new MedicalServiceDto());
        return "service/saveService";
    }
    
    @PostMapping("/service/saveService")
    public String saveService(
        @Valid @ModelAttribute("medicalService") MedicalServiceDto medicalServiceDto,
        BindingResult result,
        @AuthenticationPrincipal UserDetails userDetails
        ){
            Long patient_id = userService.findUserByEmail(userDetails.getUsername()).getPatient().getId();
            medicalServService.saveMedicalService(medicalServiceDto, patient_id);
            return "redirect:/service/service-menu";
    }

    @GetMapping("/service/service-menu")
    public String showServiceMenu(
        @AuthenticationPrincipal UserDetails userDetails,
        Model model) {
        Long patient_id = userService.findUserByEmail(userDetails.getUsername()).getPatient().getId();
        model.addAttribute("patient", patient_id);
        return "service/service-menu";
    }

    @GetMapping("/service/patient-services/{id}")
    public String showCurrentPatientService(
        @PathVariable Long id,
        @AuthenticationPrincipal UserDetails userDetails,
        Model model) {
        Long patient_id = userService.findUserByEmail(userDetails.getUsername()).getPatient().getId();
        
        if (patient_id == id) {
            model.addAttribute("services", medicalServService.listMedicalServicesByPatient(patientService.findPatientById(patient_id)));
            return "service/patient-services";
        }
        return "service/service-menu";
    }

}
