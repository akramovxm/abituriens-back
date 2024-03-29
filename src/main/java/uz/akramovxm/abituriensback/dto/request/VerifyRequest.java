package uz.akramovxm.abituriensback.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class VerifyRequest {
    @NotNull(message = "email must not be null")
    @NotBlank(message = "email must not be empty")
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "email is not valid")
    private String email;

    @NotNull(message = "verifyCode must not be null")
    @NotBlank(message = "verifyCode must not be empty")
    private String verifyCode;
}
