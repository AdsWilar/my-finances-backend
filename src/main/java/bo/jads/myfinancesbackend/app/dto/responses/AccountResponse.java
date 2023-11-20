package bo.jads.myfinancesbackend.app.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountResponse {

    private Long id;
    private String name;
    private String description;
    private BigDecimal initialBalance;
    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal currentBalance;
    private String icon;
    private Boolean deleted;
    private Long currencyId;
    private CurrencyResponse currency;

}
