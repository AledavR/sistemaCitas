package unmsm.hospital.sistemaCitas.controller;

import unmsm.hospital.sistemaCitas.entity.User;
import unmsm.hospital.sistemaCitas.entity.Role;
import unmsm.hospital.sistemaCitas.service.UserService;

import java.util.List;
import java.util.Optional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
		this.userService = userService;
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

    @PostMapping("/admin/user")
    public String giveAdminRightsSave(@Valid @ModelAttribute("email") String email,
				      @ModelAttribute("role") String role,
				      BindingResult result,
				      Model model) {

		if (email.isEmpty()){
            return "redirect:/admin/user?error";
		}
		
        User adminUser = userService.findUserByEmail(email);

		if (adminUser == null){
            return "redirect:/admin/user?error";
		}

		// TODO Corregir este error handler ya que esto es un hack.
		// Pero al menos funciona...
		// Quiza seria bueno rehacer la clase UserDto
		
        // if (adminUser == null ||
		// 	adminUser.getEmail() == null ||
		// 	!adminUser.getEmail().isEmpty()) {
        //     result.rejectValue("email", null,
		// 	       "No hay un usuario con ese correo");
        // }

        // if (result.hasErrors()) {
        // List<Role> roles = userService.listRoles();
        // model.addAttribute("email", email);
        // model.addAttribute("roles", roles);
        //     return "/admin/user";
        // }

        // userService.makeUserAdmin(email);
        userService.changeUserRoleByEmail(email, role);
        return "redirect:/admin/user?success";
    }

}
