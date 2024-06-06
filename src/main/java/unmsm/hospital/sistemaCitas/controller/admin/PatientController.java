package unmsm.hospital.sistemaCitas.controller.admin;

import unmsm.hospital.sistemaCitas.entity.Patient;
import unmsm.hospital.sistemaCitas.service.UserService;
import unmsm.hospital.sistemaCitas.service.PatientService;
import unmsm.hospital.sistemaCitas.dto.PatientDto;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PatientController {

    private final UserService userService;
    private final PatientService patientService;

    public PatientController(UserService userService,
			     PatientService patientService) {
	this.userService = userService;
	this.patientService = patientService;
    }

    @GetMapping("/admin/patient")
    public String showAddPatientForm(Model model) {
	model.addAttribute("patient", new PatientDto());
	return "admin/patient";
    }

    @PostMapping("/admin/patient")
    public String savePatient(@Valid @ModelAttribute("patient") PatientDto patientDto,
			      BindingResult result,
			      Model model) {
	
	if (result.hasErrors()) {
	    model.addAttribute("patient", patientDto);
	    return "admin/patient";
	}
	patientService.savePatient(patientDto);
	return "redirect:/admin/patient?success";
    }

    @GetMapping("/list/patients")
    public String patients(Model model){
        List<Patient> patients = patientService.listPatients();
        model.addAttribute("patients", patients);
        return "list/patients";
    }

}
