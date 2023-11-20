package bo.jads.myfinancesbackend.app.controllers;

import bo.jads.myfinancesbackend.app.dto.responses.CurrencyResponse;
import bo.jads.myfinancesbackend.app.dto.responses.GeneralResponse;
import bo.jads.myfinancesbackend.app.dto.responses.enums.ResponseTitle;
import bo.jads.myfinancesbackend.app.services.CurrencyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/currencies")
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping
    public GeneralResponse<List<CurrencyResponse>> getAllCurrencies() {
        List<CurrencyResponse> response = currencyService.getAllCurrencies();
        return new GeneralResponse<>(
                ResponseTitle.CURRENCIES, true, "Currencies obtained successfully.", response
        );
    }

}
