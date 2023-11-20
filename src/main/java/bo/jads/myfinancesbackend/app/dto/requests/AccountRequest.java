package bo.jads.myfinancesbackend.app.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AccountRequest {

    @NotBlank(message = "The name is required")
    private String name;
    private String description;
    @NotNull(message = "The initial balance is required")
    @Positive(message = "The initial balance must be positive")
    private BigDecimal initialBalance;
    private String icon;
    @NotNull(message = "The currency id is required")
    @Positive(message = "The currency id must be positive")
    private Long currencyId;

}
