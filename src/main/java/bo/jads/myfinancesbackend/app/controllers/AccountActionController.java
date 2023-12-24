package bo.jads.myfinancesbackend.app.controllers;

import bo.jads.myfinancesbackend.app.dto.responses.AccountActionResponse;
import bo.jads.myfinancesbackend.app.dto.responses.GeneralResponse;
import bo.jads.myfinancesbackend.app.dto.responses.enums.ResponseTitle;
import bo.jads.myfinancesbackend.app.services.AccountActionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/account-actions")
public class AccountActionController {

    private final AccountActionService accountActionService;

    @GetMapping
    public GeneralResponse<List<AccountActionResponse>> getAllAccountActions() {
        List<AccountActionResponse> response = accountActionService.getAllAccountActions();
        return new GeneralResponse<>(
                ResponseTitle.ACCOUNT_ACTIONS, true, "Account Actions obtained successfully.", response
        );
    }

}
