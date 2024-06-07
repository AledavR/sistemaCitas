package unmsm.hospital.sistemaCitas.repository;

import unmsm.hospital.sistemaCitas.entity.Specialty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * Repositorio de especialidades medicas.
 * <p>
 * Extiende la clase
 * {@link org.springframework.data.jpa.repository.JpaRepository JpaRepository}.
 * Usado para definir metodos que permitan realizar consultas a la
 * base de datos.
 * Para conocer los atributos de la entidad puede revisar
 * la clase {@link unmsm.hospital.sistemaCitas.entity.Specialty Specialty}.
 *
 * @author Alejandro Ramirez
 * @since 0.1
 */
@Repository
public interface SpecialtyRepository extends JpaRepository<Specialty, Long> {
    
    Specialty findByName(String name);
    
}
