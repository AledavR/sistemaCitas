package unmsm.hospital.sistemaCitas.specifications;

import unmsm.hospital.sistemaCitas.models.Paciente;
import org.springframework.data.jpa.domain.Specification;

public class PacienteSpecifications {
	public static Specification<Paciente> hasNombre(String nombre) {
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.<String>get("nombre"), "%" + nombre + "%");
	}

	public static Specification<Paciente> hasApellido(String apellido) {
		return (root, query, criteriaBuilder) ->
			criteriaBuilder.like(root.<String>get("apellido"), "%" + apellido + "%");
	}

}
