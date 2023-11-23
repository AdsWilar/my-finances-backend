package bo.jads.myfinancesbackend.app.dto.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PasswordResetRequest {

    @NotBlank(message = "The username or email is required")
    private String usernameOrEmail;

}
