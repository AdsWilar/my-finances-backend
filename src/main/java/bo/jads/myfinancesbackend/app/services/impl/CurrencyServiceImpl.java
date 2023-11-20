package bo.jads.myfinancesbackend.app.services.impl;

import bo.jads.myfinancesbackend.app.dto.responses.CurrencyResponse;
import bo.jads.myfinancesbackend.app.services.CurrencyService;
import bo.jads.myfinancesbackend.app.usecases.currencies.GetAllCurrencies;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final GetAllCurrencies getAllCurrencies;

    @Override
    public List<CurrencyResponse> getAllCurrencies() {
        return getAllCurrencies.execute();
    }

}
