package bo.jads.myfinancesbackend.app.controllers;

import bo.jads.myfinancesbackend.app.dto.requests.AccountRequest;
import bo.jads.myfinancesbackend.app.dto.requests.UserAccountRequest;
import bo.jads.myfinancesbackend.app.dto.responses.AccountResponse;
import bo.jads.myfinancesbackend.app.dto.responses.GeneralResponse;
import bo.jads.myfinancesbackend.app.dto.responses.UserAccountResponse;
import bo.jads.myfinancesbackend.app.dto.responses.enums.ResponseTitle;
import bo.jads.myfinancesbackend.app.exceptions.accounts.AccountAlreadyRegisteredException;
import bo.jads.myfinancesbackend.app.exceptions.accounts.AccountNotFoundException;
import bo.jads.myfinancesbackend.app.exceptions.currencies.CurrencyNotFoundException;
import bo.jads.myfinancesbackend.app.exceptions.useraccounts.UserAccountException;
import bo.jads.myfinancesbackend.app.exceptions.users.UserNotFoundException;
import bo.jads.myfinancesbackend.app.services.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public GeneralResponse<AccountResponse> registerAccount(@Valid @RequestBody AccountRequest request)
            throws AccountAlreadyRegisteredException, CurrencyNotFoundException {
        AccountResponse response = accountService.registerAccount(request);
        return new GeneralResponse<>(
                ResponseTitle.ACCOUNTS, true, "Account registered successfully.", response
        );
    }

    @PostMapping("/user-accounts")
    public GeneralResponse<UserAccountResponse> associateUserToAccount(@Valid @RequestBody UserAccountRequest request)
            throws UserNotFoundException, AccountNotFoundException, UserAccountException {
        UserAccountResponse response = accountService.associateUserToAccount(request);
        return new GeneralResponse<>(
                ResponseTitle.ACCOUNTS, true, "User associated to account successfully.", response
        );
    }

    @PutMapping("/user-accounts/{userAccountId}")
    public GeneralResponse<UserAccountResponse> deactivateUserAccount(@PathVariable("userAccountId") Long userAccountId)
            throws UserAccountException {
        UserAccountResponse response = accountService.deactivateUserAccount(userAccountId);
        return new GeneralResponse<>(
                ResponseTitle.ACCOUNTS, true, "User Account successfully deactivated.", response
        );
    }

}
