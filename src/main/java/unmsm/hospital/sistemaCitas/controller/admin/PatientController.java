package unmsm.hospital.sistemaCitas.controller.admin;

import unmsm.hospital.sistemaCitas.entity.Patient;
import unmsm.hospital.sistemaCitas.entity.User;
import unmsm.hospital.sistemaCitas.service.UserService;
import unmsm.hospital.sistemaCitas.service.PatientService;

import java.util.List;
import jakarta.validation.constraints.Email;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String savePatient(@RequestParam("email") @Email String email, Model model) {
        if (email.isEmpty()) {
            return "redirect:/admin/patient?error=empty";
        }

        if (!email.contains("@") || !email.contains(".")) { //(FALTA)falta cubrir caso de símbolos 
            //no correspondientes al formato del Correo
            return "redirect:/admin/patient?error=invalid"; // nueva validación para error de formato del email
        }

        User patientUser = userService.findUserByEmail(email);
        if (patientUser == null) {
            return "redirect:/admin/patient?error=notfound";
        }

        if (patientService.patientExists(patientUser.getId())) {
            return "redirect:/admin/patient?error=exists"; // nueva validación para correo ya existente
        }

        patientService.savePatient(patientUser.getId());
        return "redirect:/admin/patient?success=true";
    }

    @GetMapping("/list/patients")
    public String patients(Model model) {
        List<Patient> patients = patientService.listPatients();
        model.addAttribute("patients", patients);
        return "list/patients";
    }

    @GetMapping("/patients/{id}")
    public String viewPatient(@PathVariable Long id, Model model) {
        User user = patientService.findPatientById(id).getUser();
        if (user == null) {
            return "error-view";
        }
        model.addAttribute("user", user);
        return "patient-view";
    }

    @GetMapping("/patientUpdate")//usar @GetMapping("/patientUpdate?id={id}) aunque ya funciona
    public String showUpdatePatientForm(@RequestParam("id") Long id, Model model) {
        User patient = patientService.findPatientById(id).getUser();
        if (patient == null) {
            model.addAttribute("error", true);
            return "patientUpdate";
        }
        model.addAttribute("user", patient);
        return "patientUpdate";
    }

    @PostMapping("/patientUpdate")
    public String updatePatient(@ModelAttribute("user") User user,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            // Manejar errores de validación si es necesario
            return "patientUpdate"; // Vista para mostrar el formulario de actualización con errores
        }

        // Guardar los cambios en el servicio de usuarios
        patientService.updatePatient(user);

        return "redirect:/list/patients";
    }

    //método para filtrar al paciente
    @GetMapping("/search")
    public String searchPatientsByEmail(@RequestParam("email") String email, Model model) {
        
        //Añadir la lógica si es necesario y sí lo es
        List<Patient> patients = patientService.findPatientByEmail(email);

        //Añadiendo los pacientes encontrados al modelo
        model.addAttribute("patient", patients);

        // Devolviendo la vista
        return "list/patients";
    }

    //Método para eliminar al paciente, mas no al usuario
    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable("id") Long id, Model model) {
        patientService.deletePatientById(id); // Elimina al paciente por su ID

        List<Patient> patients = patientService.listPatients(); // Vuelve a cargar la lista de pacientes
        model.addAttribute("patients", patients); // Agrega la lista actualizada al modelo
        
        return "redirect:/list/patients"; // Redirige de vuelta a la lista de pacientes
    }

    // Otros métodos para manejar operaciones de creación, eliminación, etc.
}
