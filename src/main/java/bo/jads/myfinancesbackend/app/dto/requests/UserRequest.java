package bo.jads.myfinancesbackend.app.dto.requests;

import bo.jads.myfinancesbackend.app.dto.FileDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequest {

    @NotBlank(message = "The username is required")
    private String username;
    @NotBlank(message = "The password is required")
    @Size(message = "The password must be at least 8 characters", min = 8)
    private String password;
    @NotBlank(message = "The password confirmation is required")
    @Size(message = "The password confirmation must be at least 8 characters", min = 8)
    private String passwordConfirmation;
    @NotBlank(message = "Names are required")
    private String names;
    private String firstSurname;
    private String secondSurname;
    @Email(message = "The email is not in a valid format",
            regexp = "([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+")
    private String email;
    private String phone;
    private FileDto photo;

}
