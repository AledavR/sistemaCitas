package unmsm.hospital.sistemaCitas.service.impl;

import unmsm.hospital.sistemaCitas.entity.Role;
import unmsm.hospital.sistemaCitas.entity.User;
import unmsm.hospital.sistemaCitas.repository.RoleRepository;
import unmsm.hospital.sistemaCitas.repository.UserRepository;
import unmsm.hospital.sistemaCitas.dto.UserDto;
import unmsm.hospital.sistemaCitas.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
    private UserRepository userRepository;
	@Autowired
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());
        // encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_PATIENT");
        if(role == null){
            role = checkRoleExist("ROLE_PATIENT");
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

	@Override
	public void changeUserRoleByEmail(String email, String role_name){
		User user = userRepository.findByEmail(email);
		Role role = roleRepository.findByName(role_name);
        if(role == null){
            role = checkRoleExist(role_name);
        }
		List<Role> roles = new ArrayList<>(List.of(role));
        user.setRoles(roles);
        userRepository.save(user);
	}

	@Override
	public List<Role> listRoles(){
		List<Role> roles = roleRepository.findAll();
		return roles;
	}

    private UserDto mapToUserDto(User user){
				UserDto userDto = new UserDto();
				String[] str = user.getName().split(" ");
				userDto.setFirstName(str[0]);
				userDto.setLastName(str[1]);
				userDto.setEmail(user.getEmail());
				return userDto;
	}

	//Estuve cambiando esto
	private Role checkRoleExist(String role_name){
		Role role = new Role();
		role.setName(role_name);
		return roleRepository.save(role);
	}

	private Role checkRoleAdminExist(){
		Role role = new Role();
		role.setName("ROLE_ADMIN");
		return roleRepository.save(role);
	}
}
