package uz.akramovxm.abituriensback.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    @NotNull(message = "firstName must not be null")
    @NotBlank(message = "firstName must not be empty")
    private String firstName;

    @NotNull(message = "lastName must not be null")
    @NotBlank(message = "lastName must not be empty")
    private String lastName;

    @NotNull(message = "email must not be null")
    @NotBlank(message = "email must not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "email is not valid")
    private String email;

    @NotNull(message = "password must not be null")
    @NotBlank(message = "password must not be empty")
    private String password;

    private String phoneNumber;

    private LocalDate birthDate;
}
