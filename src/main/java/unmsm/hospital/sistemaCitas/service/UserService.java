package unmsm.hospital.sistemaCitas.service;

import unmsm.hospital.sistemaCitas.dto.UserDto;
import unmsm.hospital.sistemaCitas.entity.User;
import unmsm.hospital.sistemaCitas.entity.Role;

import java.util.List;

public interface UserService {

	void saveUser(UserDto userDto);

	User findUserByEmail(String email);

	List<UserDto> findAllUsers();

	// void makeUserAdmin(String email);

	void changeUserRoleByEmail(String email, String role_name);

	List<Role> listRoles();
}
