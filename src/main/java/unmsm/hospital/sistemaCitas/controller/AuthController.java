package unmsm.hospital.sistemaCitas.controller;

import unmsm.hospital.sistemaCitas.dto.UserDto;
import unmsm.hospital.sistemaCitas.entity.User;
import unmsm.hospital.sistemaCitas.service.UserService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register")
    public String registration(@Valid @ModelAttribute("user") UserDto userDto,
                               BindingResult result,
                               Model model){
        User existingUser = userService.findUserByEmail(userDto.getEmail());

        if(existingUser != null &&
           existingUser.getEmail() != null &&
           !existingUser.getEmail().isEmpty())
            {
                result.rejectValue("email", null,
                                   "Ya hay una cuenta registrada con ese correo");
            }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "register";
        }

        userService.saveUser(userDto);
        return "redirect:/register?success";
    }

    // handler method to handle list of users
    @GetMapping("/list/users")
    public String users(Model model){
        List<UserDto> users = userService.findAllUsers();
        model.addAttribute("users", users);
        return "list/users";
    }

    // handler method to handle login request
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
