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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;


import java.util.List;

@RestController
@RequestMapping("pacientes")
public class ControladorPaciente {
	@Autowired
	ServicioPaciente servicioPaciente;

	@GetMapping(value = "/", produces = "application/json")
	public List<Paciente> listaPacientes(){
		return servicioPaciente.list();
	}

	@PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
	public Paciente save(@RequestBody Paciente paciente){
		return servicioPaciente.save(paciente);

	}

	@GetMapping(value = "/{id}", produces = "application/json")
	public Paciente get(@PathVariable Long id){
		return servicioPaciente.get(id);
	}

	// Busqueda por nombre usando parametro de url
	// @GetMapping(value = "/nombre/{nombre}", produces = "application/json")
	// public Paciente get(@PathVariable String nombre) {
	// 	return servicioPaciente.getByNombre(nombre);
	// }

	// Busqueda por nombre usando atributos de url
	@GetMapping(value = "/nombre", produces = "application/json")
	public Paciente getByName(@RequestParam String nombre) {
		return servicioPaciente.getByNombre(nombre);
	}

	@PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
	public Paciente update(@PathVariable Long id, @RequestBody Paciente paciente) {
		return servicioPaciente.update(id, paciente);
	}

	@DeleteMapping(value = "/{id}", produces = "application/json")
	public void delete(@PathVariable Long id) {
		servicioPaciente.delete(id);
	}
}
