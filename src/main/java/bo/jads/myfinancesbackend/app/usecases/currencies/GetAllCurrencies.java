package bo.jads.myfinancesbackend.app.usecases.currencies;

import bo.jads.myfinancesbackend.app.domain.entities.Currency;
import bo.jads.myfinancesbackend.app.domain.repositories.CurrencyRepository;
import bo.jads.myfinancesbackend.app.dto.responses.CurrencyResponse;
import bo.jads.myfinancesbackend.app.mappers.CurrencyMapper;
import bo.jads.myfinancesbackend.app.usecases.FindAllUseCase;
import org.springframework.stereotype.Component;

@Component
public class GetAllCurrencies extends FindAllUseCase<Currency, CurrencyRepository, CurrencyResponse, CurrencyMapper> {

    public GetAllCurrencies(CurrencyRepository repository, CurrencyMapper currencyMapper) {
        super(repository, currencyMapper);
    }

}
