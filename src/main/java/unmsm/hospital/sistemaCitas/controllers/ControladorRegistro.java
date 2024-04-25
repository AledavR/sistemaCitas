package unmsm.hospital.sistemaCitas.controllers;

import unmsm.hospital.sistemaCitas.models.Paciente;
import unmsm.hospital.sistemaCitas.models.Doctor;
import unmsm.hospital.sistemaCitas.services.ServicioPaciente;
import unmsm.hospital.sistemaCitas.services.ServicioDoctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
@RequestMapping("registro")
public class ControladorRegistro {

	@Autowired
	ServicioPaciente servicioPaciente;

	@Autowired
	ServicioDoctor servicioDoctor;

	@GetMapping("/paciente")
	public String registerPacienteGet(Model model){
		model.addAttribute("pacienteForm", new Paciente());
		return "registro/paciente";
	}

	@PostMapping("/paciente")
	public String registerPacientePost(@ModelAttribute("pacienteForm") Paciente paciente) {
		System.out.print(paciente.toString());
		servicioPaciente.save(paciente);
		return "registro/agradecer";
	}

	@GetMapping("/doctor")
	public String registerDoctorGet(Model model){
		model.addAttribute("doctorForm", new Doctor());
		return "registro/doctor";
	}

	@PostMapping("/doctor")
	public String registerDoctorPost(@ModelAttribute("doctorForm") Doctor doctor) {
		System.out.print(doctor.toString());
		servicioDoctor.save(doctor);
		return "registro/agradecer";
	}
	
}
