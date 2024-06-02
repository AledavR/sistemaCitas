package unmsm.hospital.sistemaCitas.controller.admin;

import unmsm.hospital.sistemaCitas.entity.Specialty;
import unmsm.hospital.sistemaCitas.service.SpecialtyService;
import unmsm.hospital.sistemaCitas.dto.SpecialtyDto;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    public SpecialtyController(SpecialtyService specialtyService){
		this.specialtyService = specialtyService;
    }

    @GetMapping("/admin/specialty")
    public String showSpecialtyForm(Model model) {
        SpecialtyDto specialty = new SpecialtyDto();
        model.addAttribute("specialty", specialty);
        return "admin/specialty";
    }

    @PostMapping("/admin/specialty")
    public String saveSpecialty(@Valid @ModelAttribute("specialty") SpecialtyDto specialtyDto,
								BindingResult result,
								Model model) {
        Specialty existingSpecialty = specialtyService
	    .findSpecialtyByName(specialtyDto.getName());

        if (existingSpecialty != null
	    && existingSpecialty.getName() != null
	    && !existingSpecialty.getName().isEmpty()) {
            result.rejectValue("name", null,
			       "Esa especialidad ya esta registrada");
        }

        if (result.hasErrors()) {
            model.addAttribute("specialty", specialtyDto);
            return "admin/specialty";
        }

        specialtyService.saveSpecialty(specialtyDto);
        return "redirect:/admin/specialty?success";
    }

    @GetMapping("/specialties")
    public String specialties(Model model){
        List<Specialty> specialties = specialtyService.listSpecialties();
        model.addAttribute("specialties", specialties);
        return "specialties";
    }
	

}
