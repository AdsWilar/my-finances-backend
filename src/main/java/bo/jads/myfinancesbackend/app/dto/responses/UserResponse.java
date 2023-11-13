package bo.jads.myfinancesbackend.app.dto.responses;

import bo.jads.myfinancesbackend.app.domain.entities.enums.UserStatus;
import bo.jads.myfinancesbackend.app.dto.FileDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

    private Long id;
    private String username;
    private String code;
    private UserStatus status;
    private String names;
    private String firstSurname;
    private String secondSurname;
    private String email;
    private String phone;
    private FileDto photo;

}
