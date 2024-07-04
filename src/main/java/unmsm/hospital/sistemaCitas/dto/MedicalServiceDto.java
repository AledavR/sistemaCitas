package unmsm.hospital.sistemaCitas.dto;

import java.sql.Date;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicalServiceDto {


    private Long id;

    // @NotEmpty(message = "Ingrese los detalles del servicio")
    // private String details;

    @NotNull(message = "Elija un medico")
    private Long doctor_id;

    @NotNull(message = "Elija un tipo de servicio")
    private Long type_id;

    @NotNull(message = "Elija una especialidad")
    private Long specialty_id;

    @NotNull(message = "Elija una fecha")
    private Date service_date;

}
