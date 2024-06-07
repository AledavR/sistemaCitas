package unmsm.hospital.sistemaCitas.controller.admin;

import unmsm.hospital.sistemaCitas.entity.Doctor;
import unmsm.hospital.sistemaCitas.entity.DoctorDirectory;
import unmsm.hospital.sistemaCitas.entity.Specialty;
import unmsm.hospital.sistemaCitas.entity.SpecialtyInfo;
import unmsm.hospital.sistemaCitas.service.SpecialtyService;
import unmsm.hospital.sistemaCitas.service.SpecialtyInfoService;
import unmsm.hospital.sistemaCitas.dto.SpecialtyDto;

import java.util.List;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SpecialtyController {

    private final SpecialtyService specialtyService;
    private final SpecialtyInfoService specialtyInfoService;

    public SpecialtyController(SpecialtyService specialtyService , SpecialtyInfoService specialtyInfoService){
		this.specialtyService = specialtyService;
        this.specialtyInfoService = specialtyInfoService;
    }

    @GetMapping("/admin/specialty")
    public String showSpecialtyForm(Model model) {
        model.addAttribute("specialty", new SpecialtyDto());
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
        model.addAttribute("specialtiesInfo", specialtyInfoService.listSpecialtiesInfo());
        return "specialties";
    }

    @GetMapping("/specialties/{name}")
	public String viewSpecialties(@PathVariable String name, Model model) {
		SpecialtyInfo specialtyInfo = specialtyInfoService.findSpecialtyInfoById(specialtyService.findSpecialtyByName(name).getId());
		model.addAttribute("specialtyInfo", specialtyInfo);
		return "specialty-view";
	}

}
