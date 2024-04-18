package unmsm.hospital.sistemaCitas.controllers;

import unmsm.hospital.sistemaCitas.models.Paciente;
import unmsm.hospital.sistemaCitas.services.ServicioPaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.template.PathBasedTemplateAvailabilityProvider;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import java.util.List;

@Controller
@RequestMapping("registro")
public class ControladorRegistro {
	@Autowired
	ServicioPaciente servicioPaciente;

	@GetMapping("/paciente")
	public String register(Model model){
		model.addAttribute("pacienteForm", new Paciente());
		return "registro/paciente";
	}

	@PostMapping("/paciente")
	public String registerPaciente(@ModelAttribute("pacienteForm") Paciente paciente) {
		System.out.print(paciente.toString());
		servicioPaciente.save(paciente);
		return "registro/paciente";
	}
}
