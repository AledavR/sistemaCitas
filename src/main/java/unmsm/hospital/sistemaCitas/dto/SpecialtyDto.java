package unmsm.hospital.sistemaCitas.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SpecialtyDto{
    private Long id;

    @NotEmpty(message = "Ingrese el nombre de la especialidad")
    private String name;
}
