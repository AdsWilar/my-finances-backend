package bo.jads.myfinancesbackend.app.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAccountResponse {

    private Long id;
    private Boolean isActive;
    private Boolean isOwner;
    private Long userId;
    private Long accountId;
    private UserResponse user;
    private AccountResponse account;

}
