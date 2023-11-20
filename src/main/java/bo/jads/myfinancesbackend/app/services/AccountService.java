package bo.jads.myfinancesbackend.app.services;

import bo.jads.myfinancesbackend.app.dto.requests.AccountRequest;
import bo.jads.myfinancesbackend.app.dto.requests.UserAccountRequest;
import bo.jads.myfinancesbackend.app.dto.responses.AccountResponse;
import bo.jads.myfinancesbackend.app.dto.responses.UserAccountResponse;
import bo.jads.myfinancesbackend.app.exceptions.accounts.AccountAlreadyRegisteredException;
import bo.jads.myfinancesbackend.app.exceptions.accounts.AccountNotFoundException;
import bo.jads.myfinancesbackend.app.exceptions.currencies.CurrencyNotFoundException;
import bo.jads.myfinancesbackend.app.exceptions.useraccounts.UserAccountException;
import bo.jads.myfinancesbackend.app.exceptions.users.UserNotFoundException;

public interface AccountService {

    AccountResponse registerAccount(AccountRequest request) throws AccountAlreadyRegisteredException,
            CurrencyNotFoundException;

    UserAccountResponse associateUserToAccount(UserAccountRequest request) throws UserNotFoundException,
            AccountNotFoundException, UserAccountException;

    UserAccountResponse deactivateUserAccount(Long userAccountId) throws UserAccountException;

}
