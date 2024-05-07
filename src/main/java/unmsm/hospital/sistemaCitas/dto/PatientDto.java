package unmsm.hospital.sistemaCitas.dto;

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
public class PatientDto {
    @NotEmpty(message = "Ingrese el nombre del paciente")
    private String firstName;

    @NotEmpty(message = "Ingrese el apellido del paciente")
    private String lastName;
    
    @NotEmpty(message = "Ingrese la dirección del paciente")
    private String address;
    
    @NotEmpty(message = "Ingrese el telefono del paciente")
    private String phone;
    
    @NotNull(message = "Ingrese la edad del paciente")
    private Integer age;

}
