package bo.jads.myfinancesbackend.app.dto.requests;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

@Getter
public class UserAccountRequest {

    @NotNull(message = "The user id is required")
    @Positive(message = "The user id must be positive")
    private Long userId;
    @NotNull(message = "The account id is required")
    @Positive(message = "The account id must be positive")
    private Long accountId;

}
