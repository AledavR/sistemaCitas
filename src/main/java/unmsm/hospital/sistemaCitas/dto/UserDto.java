package unmsm.hospital.sistemaCitas.dto;

import java.sql.Date;

import jakarta.validation.constraints.Email;
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
public class UserDto {
    
    private Long id;
    
    @NotEmpty(message = "Ingrese sus nombres")
    private String names;
    
    @NotEmpty(message = "Ingrese sus apellidos")
    private String lastnames;

    @NotNull(message = "Ingrese una fecha de nacimiento valida")
    private Date birthday;
    
    @NotEmpty(message = "Ingrese su correo")
    @Email
    private String email;
    
    @NotEmpty(message = "Ingrese su contraseña")
    private String password;

    @NotEmpty(message = "Ingrese su telefono")
    private String phone;
}
