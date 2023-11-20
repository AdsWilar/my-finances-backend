package bo.jads.myfinancesbackend.app.services;

import bo.jads.myfinancesbackend.app.dto.responses.CurrencyResponse;

import java.util.List;

public interface CurrencyService {

    List<CurrencyResponse> getAllCurrencies();

}
