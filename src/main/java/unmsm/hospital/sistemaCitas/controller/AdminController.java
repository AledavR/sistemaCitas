package unmsm.hospital.sistemaCitas.controller;

import jakarta.validation.Valid;
import unmsm.hospital.sistemaCitas.entity.User;
import unmsm.hospital.sistemaCitas.entity.Role;
import unmsm.hospital.sistemaCitas.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {

	private UserService userService;

	public AdminController(UserService userService){
		this.userService = userService;
	}

	@GetMapping("/admin")
	public String showAdminMenu(){
		return "admin";
	}

	@GetMapping("/admin/user")
	public String giveAdminRights(Model model){
		String userEmail = new String();
		List<Role> roles = userService.listRoles();
		model.addAttribute("email", userEmail);
		model.addAttribute("roles", roles);
		return "admin/user";
	}

	@PostMapping("/admin/user/save")
	public String giveAdminRightsSave(@ModelAttribute("email") String email,
									  @ModelAttribute("roles") String role_name,
									  BindingResult result,
									  Model model){
		User adminUser = userService.findUserByEmail(email);
		
        if(adminUser == null && adminUser.getEmail() == null && !adminUser.getEmail().isEmpty()){
            result.rejectValue("email", null,
                    "No hay un usuario con ese correo");
        }
		
        if(result.hasErrors()){
            model.addAttribute("user", email);
            return "/admin/user";
        }

		// userService.makeUserAdmin(email);
		userService.changeUserRoleByEmail(email,role_name);
        return "redirect:/admin/user?success";
	}

}
