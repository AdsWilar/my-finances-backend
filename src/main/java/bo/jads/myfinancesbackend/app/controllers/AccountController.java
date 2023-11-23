package bo.jads.myfinancesbackend.app.controllers;

import bo.jads.myfinancesbackend.app.access.interceptor.ResourceAction;
import bo.jads.myfinancesbackend.app.domain.entities.enums.ActionCode;
import bo.jads.myfinancesbackend.app.domain.entities.enums.EntityType;
import bo.jads.myfinancesbackend.app.dto.requests.AccountRequest;
import bo.jads.myfinancesbackend.app.dto.requests.UserAccountRequest;
import bo.jads.myfinancesbackend.app.dto.responses.AccountResponse;
import bo.jads.myfinancesbackend.app.dto.responses.GeneralResponse;
import bo.jads.myfinancesbackend.app.dto.responses.UserAccountResponse;
import bo.jads.myfinancesbackend.app.dto.responses.enums.ResponseTitle;
import bo.jads.myfinancesbackend.app.exceptions.accounts.AccountAlreadyRegisteredException;
import bo.jads.myfinancesbackend.app.exceptions.entitynotfound.CurrencyNotFoundException;
import bo.jads.myfinancesbackend.app.exceptions.entitynotfound.EntityNotFoundException;
import bo.jads.myfinancesbackend.app.exceptions.entitynotfound.UserAccountNotFoundException;
import bo.jads.myfinancesbackend.app.exceptions.forbidden.UnauthorizedUserAccountException;
import bo.jads.myfinancesbackend.app.exceptions.useraccounts.AlreadyInactiveUserAccountException;
import bo.jads.myfinancesbackend.app.exceptions.useraccounts.UserAccountAlreadyRegisteredException;
import bo.jads.myfinancesbackend.app.services.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @ResourceAction(action = ActionCode.ACCOUNT_CREATION, entity = EntityType.ACCOUNT)
    @PostMapping
    public GeneralResponse<AccountResponse> registerAccount(@Valid @RequestBody AccountRequest request)
            throws AccountAlreadyRegisteredException, CurrencyNotFoundException {
        AccountResponse response = accountService.registerAccount(request);
        return new GeneralResponse<>(
                ResponseTitle.ACCOUNTS, true, "Account registered successfully.", response
        );
    }

    @ResourceAction(action = ActionCode.USER_TO_ACCOUNT_ASSOCIATION, entity = EntityType.USER_ACCOUNT)
    @PostMapping("/user-accounts")
    public GeneralResponse<UserAccountResponse> associateUserToAccount(@Valid @RequestBody UserAccountRequest request)
            throws EntityNotFoundException, UnauthorizedUserAccountException, UserAccountAlreadyRegisteredException {
        UserAccountResponse response = accountService.associateUserToAccount(request);
        return new GeneralResponse<>(
                ResponseTitle.ACCOUNTS, true, "User associated to account successfully.", response
        );
    }

    @ResourceAction(action = ActionCode.USER_ACCOUNT_DEACTIVATION, entity = EntityType.USER_ACCOUNT)
    @PutMapping("/user-accounts/{userAccountId}")
    public GeneralResponse<UserAccountResponse> deactivateUserAccount(@PathVariable("userAccountId") Long userAccountId)
            throws UserAccountNotFoundException, AlreadyInactiveUserAccountException, UnauthorizedUserAccountException {
        UserAccountResponse response = accountService.deactivateUserAccount(userAccountId);
        return new GeneralResponse<>(
                ResponseTitle.ACCOUNTS, true, "User Account successfully deactivated.", response
        );
    }

}
