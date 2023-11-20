package bo.jads.myfinancesbackend.app.mappers;

import bo.jads.myfinancesbackend.app.domain.entities.Currency;
import bo.jads.myfinancesbackend.app.dto.responses.CurrencyResponse;
import org.mapstruct.Mapper;

@Mapper
public interface CurrencyMapper extends EntityToResponseMapper<Currency, CurrencyResponse> {
}
