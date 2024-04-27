package unmsm.hospital.sistemaCitas.repository;

import unmsm.hospital.sistemaCitas.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	/**
	 * Este metodo permite encontrar un usuario en la base de
	 * datos usando su correo.
	 * 
	 * @since 2024-04-27
	 */
	User findByEmail(String email);
	
}
