package unmsm.hospital.sistemaCitas.dto;

import unmsm.hospital.sistemaCitas.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;
    @NotEmpty(message = "Ingrese su nombre")
    private String firstName;
    @NotEmpty(message = "Ingrese su apellido")
    private String lastName;
    @NotEmpty(message = "Ingrese su correo")
    @Email
    private String email;
    @NotEmpty(message = "Ingrese su contraseña")
    private String password;
}
