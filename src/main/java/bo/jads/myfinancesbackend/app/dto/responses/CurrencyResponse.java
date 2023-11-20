package bo.jads.myfinancesbackend.app.dto.responses;

import bo.jads.myfinancesbackend.app.domain.entities.enums.CurrencyAcronym;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CurrencyResponse {

    private Long id;
    private String name;
    private CurrencyAcronym acronym;
    private String symbol;

}
