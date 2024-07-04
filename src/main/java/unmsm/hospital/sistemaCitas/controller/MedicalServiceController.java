package unmsm.hospital.sistemaCitas.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import unmsm.hospital.sistemaCitas.dto.MedicalServiceDto;
import unmsm.hospital.sistemaCitas.service.DoctorService;
import unmsm.hospital.sistemaCitas.service.MedicalServService;
import unmsm.hospital.sistemaCitas.service.SpecialtyService;
import unmsm.hospital.sistemaCitas.service.UserService;

@Controller
public class MedicalServiceController {

    private final SpecialtyService specialtyService;
    private final DoctorService doctorService;
    private final UserService userService;
    private final MedicalServService medicalServService;

    public MedicalServiceController(
        SpecialtyService specialtyService,
        DoctorService doctorService,
        UserService userService,
        MedicalServService medicalServService
    ){
        this.specialtyService = specialtyService;
        this.doctorService = doctorService;
        this.userService = userService;
        this.medicalServService = medicalServService;
    }

    @GetMapping("/service-options")
    public String showServiceOptions(Model model){
        model.addAttribute("specialties", specialtyService.listSpecialties());
        return "service-options";
    }

    @GetMapping("/saveService")
    public String showServiceForm(
        @RequestParam Long specialty_id,
        Model model
    ){
        model.addAttribute("specialty", specialtyService.findSpecialtyById(specialty_id));
        model.addAttribute("doctors", specialtyService.listDoctorsBySpecialtyId(specialty_id));
        model.addAttribute("types", medicalServService.listMedicalServiceTypes());
        model.addAttribute("medicalService", new MedicalServiceDto());
        return "saveService";
    }
    
    @PostMapping("/saveService")
    public String saveService(
        @Valid @ModelAttribute("medicalService") MedicalServiceDto medicalServiceDto,
        BindingResult result,
        @AuthenticationPrincipal UserDetails userDetails
        ){
            Long patient_id = userService.findUserByEmail(userDetails.getUsername()).getPatient().getId();
            medicalServService.saveMedicalService(medicalServiceDto, patient_id);
            return "redirect:/service-options";
    }

}
