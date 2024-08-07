package unmsm.hospital.sistemaCitas.controller.admin;

import unmsm.hospital.sistemaCitas.entity.Specialty;
import unmsm.hospital.sistemaCitas.service.SpecialtyService;
import unmsm.hospital.sistemaCitas.dto.SpecialtyDto;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.apache.commons.lang3.StringUtils;

@Controller
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    public SpecialtyController(SpecialtyService specialtyService){
        this.specialtyService = specialtyService;
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
            .findSpecialtyByName(StringUtils.stripAccents(specialtyDto.getRealname()).toLowerCase());
        
        if (existingSpecialty != null
            && existingSpecialty.getName() != null
            && !existingSpecialty.getName().isEmpty()) {
            result.rejectValue("realname", null,
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
        model.addAttribute("specialties", specialtyService.listSpecialties());
        return "specialties";
    }

    @GetMapping("/specialties/{name}")
    public String viewSpecialties(@PathVariable String name, Model model) {
        Specialty specialty = specialtyService.findSpecialtyByName(name);
        model.addAttribute("specialty", specialty);
        return "specialty-view";
    }

}
