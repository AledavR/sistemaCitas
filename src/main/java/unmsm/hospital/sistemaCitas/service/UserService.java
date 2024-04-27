package unmsm.hospital.sistemaCitas.service;

import unmsm.hospital.sistemaCitas.dto.UserDto;
import unmsm.hospital.sistemaCitas.entity.User;

import java.util.List;

public interface UserService {

	void saveUser(UserDto userDto);

	User findUserByEmail(String email);

	List<UserDto> findAllUsers();

}
