package unmsm.hospital.sistemaCitas.controller;

import jakarta.validation.Valid;
import unmsm.hospital.sistemaCitas.entity.User;
import unmsm.hospital.sistemaCitas.entity.Role;
import unmsm.hospital.sistemaCitas.service.UserService;
import unmsm.hospital.sistemaCitas.entity.Specialty;
import unmsm.hospital.sistemaCitas.service.SpecialtyService;
import unmsm.hospital.sistemaCitas.dto.SpecialtyDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import unmsm.hospital.sistemaCitas.dto.DoctorDto;
import unmsm.hospital.sistemaCitas.service.DoctorService;

@Controller
public class AdminController {

    private UserService userService;
    private SpecialtyService specialtyService;
    private final DoctorService doctorService;

    public AdminController(UserService userService,
            SpecialtyService specialtyService,
            DoctorService doctorService) {
        this.userService = userService;
        this.specialtyService = specialtyService;
        this.doctorService = doctorService;
    }

    @GetMapping("/admin")
    public String showAdminMenu() {
        return "admin";
    }

    @GetMapping("/admin/user")
    public String giveAdminRights(Model model) {
        String userEmail = new String();
        String role = new String();
        List<Role> roles = userService.listRoles();
        model.addAttribute("email", userEmail);
        model.addAttribute("roles", roles);
        model.addAttribute("role", role);
        return "admin/user";
    }

    @PostMapping("/admin/user/save")
    public String giveAdminRightsSave(@ModelAttribute("email") String email,
            @ModelAttribute("role") String role,
            BindingResult result,
            Model model) {
        User adminUser = userService.findUserByEmail(email);

        if (adminUser == null
                && adminUser.getEmail() == null
                && !adminUser.getEmail().isEmpty()) {
            result.rejectValue("email", null,
                    "No hay un usuario con ese correo");
        }

        if (result.hasErrors()) {
            model.addAttribute("user", email);
            return "/admin/user";
        }

        // userService.makeUserAdmin(email);
        userService.changeUserRoleByEmail(email, role);
        return "redirect:/admin/user?success";
    }

    @GetMapping("/admin/specialty")
    public String showSpecialtyForm(Model model) {
        SpecialtyDto specialty = new SpecialtyDto();
        model.addAttribute("specialty", specialty);
        return "admin/specialty";
    }

    @PostMapping("/admin/specialty/save")
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
            return "/admin/specialty";
        }

        specialtyService.saveSpecialty(specialtyDto);
        return "redirect:/admin/specialty?success";
    }

    @GetMapping("/admin/doctor")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new DoctorDto());
        return "admin/doctor";
    }

    @PostMapping("/admin/doctor/save")
    public String saveDoctor(@Valid @ModelAttribute("doctor") DoctorDto doctorDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Falta agregar errores de validación?
            return "admin/doctor";
        }
        doctorService.saveDoctor(doctorDto);
        return "redirect:/admin/doctor?success";
    }

}
