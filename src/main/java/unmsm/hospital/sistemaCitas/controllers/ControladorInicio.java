package unmsm.hospital.sistemaCitas.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;



@Controller
@RequestMapping("")
public class ControladorInicio {

	@GetMapping("/")
	public String menuInicio() {
		System.out.println("Menu iniciado");
		return "inicio"; // Aqui se define la ubicacion del template
	}

}
