package bo.jads.myfinancesbackend.app.services;

import bo.jads.myfinancesbackend.app.dto.requests.AccountRequest;
import bo.jads.myfinancesbackend.app.dto.requests.UserAccountRequest;
import bo.jads.myfinancesbackend.app.dto.responses.AccountResponse;
import bo.jads.myfinancesbackend.app.dto.responses.UserAccountResponse;
import bo.jads.myfinancesbackend.app.exceptions.accounts.AccountAlreadyRegisteredException;
import bo.jads.myfinancesbackend.app.exceptions.entitynotfound.CurrencyNotFoundException;
import bo.jads.myfinancesbackend.app.exceptions.entitynotfound.EntityNotFoundException;
import bo.jads.myfinancesbackend.app.exceptions.entitynotfound.UserAccountNotFoundException;
import bo.jads.myfinancesbackend.app.exceptions.forbidden.UnauthorizedUserAccountException;
import bo.jads.myfinancesbackend.app.exceptions.useraccounts.AlreadyInactiveUserAccountException;
import bo.jads.myfinancesbackend.app.exceptions.useraccounts.UserAccountAlreadyRegisteredException;

public interface AccountService {

    AccountResponse registerAccount(AccountRequest request) throws AccountAlreadyRegisteredException,
            CurrencyNotFoundException;

    UserAccountResponse associateUserToAccount(UserAccountRequest request) throws EntityNotFoundException,
            UnauthorizedUserAccountException, UserAccountAlreadyRegisteredException;

    UserAccountResponse deactivateUserAccount(Long userAccountId) throws UserAccountNotFoundException,
            AlreadyInactiveUserAccountException, UnauthorizedUserAccountException;

}
