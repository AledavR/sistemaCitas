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
public class DoctorDto {
    @NotEmpty(message = "Ingrese el nombre del doctor")
    private String firstName;

    @NotEmpty(message = "Ingrese el apellido del doctor")
    private String lastName;
    
    @NotEmpty(message = "Ingrese la dirección del doctor")
    private String address;
    
    @NotEmpty(message = "Ingrese el telefono del doctor")
    private String phone;
    
    @NotEmpty(message = "Ingrese la especialidad del doctor")
    private String specialty;

}
