package unmsm.hospital.sistemaCitas.repository;

import unmsm.hospital.sistemaCitas.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	/**
	 * Este metodo permite encontrar un rol en base a su
	 * nombre
	 * 
	 * @since 2024-04-27
	 */
	Role findByName(String name);
	
}
