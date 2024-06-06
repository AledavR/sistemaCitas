package unmsm.hospital.sistemaCitas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Setter @Getter @NoArgsConstructor @AllArgsConstructor
@Table(name = "specialties_info")
public class SpecialtyInfo {

    @Id
    @Column(name = "specialty_id")
    private Long id;

    /*
	 * Esta variable guarda la "informacion" de la especialidad
	 */
    @Column(nullable = false)
    private String info;

    /*
	 * Esta variable guarda una descripcion de la especialidad
	 */
    @Column(nullable = false)
    private String description;

    @OneToOne
    @MapsId
    @JoinColumn(name = "specialty_id")
    private Specialty specialty;

}