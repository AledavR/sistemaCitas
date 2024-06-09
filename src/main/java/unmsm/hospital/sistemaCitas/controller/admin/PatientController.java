package unmsm.hospital.sistemaCitas.controller.admin;

import unmsm.hospital.sistemaCitas.entity.Patient;
import unmsm.hospital.sistemaCitas.entity.User;
import unmsm.hospital.sistemaCitas.service.UserService;
import unmsm.hospital.sistemaCitas.service.PatientService;

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
        model.addAttribute("email", new String());
        return "admin/patient";
    }

    @PostMapping("/admin/patient")
    public String savePatient(@Valid @ModelAttribute("email") String email,
                              BindingResult result,
                              Model model) {
        
        if (result.hasErrors()) {
            model.addAttribute("patient", new String());
            return "admin/patient";
        }
        if (email.isEmpty()){ return "redirect:/admin/patient?error"; }
        User patientUser = userService.findUserByEmail(email);
        if (patientUser == null){ return "redirect:/admin/patient?error"; }
        patientService.savePatient(patientUser.getId());
        return "redirect:/admin/patient?success";
    }

    @GetMapping("/list/patients")
    public String patients(Model model){
        List<Patient> patients = patientService.listPatients();
        model.addAttribute("patients", patients);
        return "list/patients";
    }

}
