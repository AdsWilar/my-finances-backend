package bo.jads.myfinancesbackend.app.usecases.currencies;

import bo.jads.myfinancesbackend.app.domain.entities.Currency;
import bo.jads.myfinancesbackend.app.domain.repositories.CurrencyRepository;
import bo.jads.myfinancesbackend.app.exceptions.entitynotfound.CurrencyNotFoundException;
import bo.jads.myfinancesbackend.app.usecases.BaseUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GetCurrencyById implements BaseUseCase<Long, Currency> {

    private final CurrencyRepository currencyRepository;

    @Override
    public Currency execute(Long id) throws CurrencyNotFoundException {
        return currencyRepository.findById(id).orElseThrow(CurrencyNotFoundException::new);
    }

}
