package unmsm.hospital.sistemaCitas.repository;

import unmsm.hospital.sistemaCitas.entity.Patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 *
 * Repositorio de pacientes.
 * <p>
 * Extiende la clase
 * {@link org.springframework.data.jpa.repository.JpaRepository JpaRepository}.
 * Usado para definir metodos que permitan realizar consultas a la
 * base de datos.
 * Para conocer los atributos de la entidad puede revisar
 * la clase {@link unmsm.hospital.sistemaCitas.Patient Patient}.
 *
 * @author Alejandro Ramirez
 * @since 0.1
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    
    /**
     * Este metodo verifica si existe un usuario en la base de
     * datos usando su id. 
     * No existe metodo propio de JPA que use boolean. 
     * Ver JpaRepository 
     *
     * @since 2024-06-18
     */
    boolean existsByUserId(Long userId); //vease en PatientServiceImpl-->patientController
    
}
