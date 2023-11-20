package bo.jads.myfinancesbackend.app.services.impl;

import bo.jads.myfinancesbackend.app.access.SessionHolder;
import bo.jads.myfinancesbackend.app.domain.entities.Account;
import bo.jads.myfinancesbackend.app.domain.entities.Currency;
import bo.jads.myfinancesbackend.app.domain.entities.User;
import bo.jads.myfinancesbackend.app.domain.entities.UserAccount;
import bo.jads.myfinancesbackend.app.dto.requests.AccountRequest;
import bo.jads.myfinancesbackend.app.dto.requests.UserAccountRequest;
import bo.jads.myfinancesbackend.app.dto.responses.AccountResponse;
import bo.jads.myfinancesbackend.app.dto.responses.UserAccountResponse;
import bo.jads.myfinancesbackend.app.exceptions.accounts.AccountAlreadyRegisteredException;
import bo.jads.myfinancesbackend.app.exceptions.accounts.AccountNotFoundException;
import bo.jads.myfinancesbackend.app.exceptions.currencies.CurrencyNotFoundException;
import bo.jads.myfinancesbackend.app.exceptions.useraccounts.*;
import bo.jads.myfinancesbackend.app.exceptions.users.UserNotFoundException;
import bo.jads.myfinancesbackend.app.mappers.AccountMapper;
import bo.jads.myfinancesbackend.app.mappers.CurrencyMapper;
import bo.jads.myfinancesbackend.app.mappers.UserMapper;
import bo.jads.myfinancesbackend.app.services.AccountService;
import bo.jads.myfinancesbackend.app.usecases.accounts.GetAccountById;
import bo.jads.myfinancesbackend.app.usecases.accounts.SaveAccount;
import bo.jads.myfinancesbackend.app.usecases.currencies.GetCurrencyById;
import bo.jads.myfinancesbackend.app.usecases.useraccounts.*;
import bo.jads.myfinancesbackend.app.usecases.users.GetUserById;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AccountServiceImpl implements AccountService {

    private final GetUserAccountByUserIdAndAccountName getUserAccountByUserIdAndAccountName;
    private final GetCurrencyById getCurrencyById;
    private final SaveAccount saveAccount;
    private final SaveAndFlushUserAccount saveAndFlushUserAccount;
    private final GetUserById getUserById;
    private final GetAccountById getAccountById;
    private final GetActiveAndOwnerUserAccountByUserIdAndAccountId getActiveAndOwnerUserAccountByUserIdAndAccountId;
    private final GetUserAccountByUserIdAndAccountId getUserAccountByUserIdAndAccountId;
    private final GetUserAccountById getUserAccountById;
    private final GetAllActiveUserAccountsByAccountId getAllActiveUserAccountsByAccountId;
    private final SaveAndFlushUserAccounts saveAndFlushUserAccounts;

    private final CurrencyMapper currencyMapper;
    private final UserMapper userMapper;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponse registerAccount(AccountRequest request) throws AccountAlreadyRegisteredException,
            CurrencyNotFoundException {
        Long loggedInUserId = SessionHolder.getInstance().getLoggedInUser().getId();
        String name = request.getName();
        try {
            UserAccount userAccount = getUserAccountByUserIdAndAccountName
                    .execute(new GetUserAccountByUserIdAndAccountName.Param(loggedInUserId, name));
            String error;
            if (userAccount.getIsActive()) {
                error = String.format("An Account with the name: %s is already registered.", name);
            } else {
                error = String.format("An Account with the name: %s is already registered, but it is INACTIVE.", name);
            }
            throw new AccountAlreadyRegisteredException(error);
        } catch (UserAccountNotFoundException ignored) {
            Currency currency = getCurrencyById.execute(request.getCurrencyId());
            AccountResponse response = saveAccount.execute(request);
            response.setCurrency(currencyMapper.entityToResponse(currency));
            saveUserAccount(true, loggedInUserId, response.getId());
            return response;
        }
    }

    @Override
    public UserAccountResponse associateUserToAccount(UserAccountRequest request) throws UserNotFoundException,
            AccountNotFoundException, UserAccountException {
        User user = getUserById.execute(request.getUserId());
        Account account = getAccountById.execute(request.getAccountId());
        try {
            getActiveAndOwnerUserAccountByUserIdAndAccountId
                    .execute(new GetActiveAndOwnerUserAccountByUserIdAndAccountId.Param(
                            SessionHolder.getInstance().getLoggedInUser().getId(), account.getId()
                    ));
            Long userId = user.getId();
            Long accountId = account.getId();
            try {
                UserAccount userAccount = getUserAccountByUserIdAndAccountId
                        .execute(new GetUserAccountByUserIdAndAccountId.Param(userId, accountId));
                if (userAccount.getIsActive()) {
                    throw new UserAccountAlreadyRegisteredException();
                }
                userAccount.setIsActive(true);
                return saveAndFlushUserAccount.execute(userAccount);
            } catch (UserAccountNotFoundException ignored) {
                UserAccountResponse response = saveUserAccount(false, userId, accountId);
                response.setUser(userMapper.entityToResponse(user));
                response.setAccount(accountMapper.entityToResponse(account));
                return response;
            }
        } catch (UserAccountNotFoundException e) {
            throw new UnauthorizedUserAccountException(e);
        }
    }

    @Override
    public UserAccountResponse deactivateUserAccount(Long userAccountId) throws UserAccountException {
        UserAccount userAccount = getUserAccountById.execute(userAccountId);
        if (!userAccount.getIsActive()) {
            throw new AlreadyInactiveUserAccountException();
        }
        Long loggedInUserId = SessionHolder.getInstance().getLoggedInUser().getId();
        if (userAccount.getIsOwner()) {
            if (!userAccount.getUserId().equals(loggedInUserId)) {
                throw new UnauthorizedUserAccountException();
            }
            UserAccountResponse response = deactivateUserAccount(userAccount);
            List<UserAccount> activeUserAccounts = getAllActiveUserAccountsByAccountId.execute(response.getAccountId());
            if (!activeUserAccounts.isEmpty()) {
                List<UserAccount> userAccountsToDeactivate = new ArrayList<>();
                activeUserAccounts.stream()
                        .filter(activeUserAccount -> !userAccount.getId().equals(activeUserAccount.getId()))
                        .forEach(activeUserAccount -> {
                            activeUserAccount.setIsActive(false);
                            userAccountsToDeactivate.add(activeUserAccount);
                        });
                saveAndFlushUserAccounts.execute(userAccountsToDeactivate);
            }
            return response;
        }
        if (userAccount.getUserId().equals(loggedInUserId)) {
            return deactivateUserAccount(userAccount);
        }
        try {
            getActiveAndOwnerUserAccountByUserIdAndAccountId
                    .execute(new GetActiveAndOwnerUserAccountByUserIdAndAccountId.Param(
                            loggedInUserId, userAccount.getAccountId()
                    ));
            return deactivateUserAccount(userAccount);
        } catch (UserAccountNotFoundException e) {
            throw new UnauthorizedUserAccountException(e);
        }
    }

    private UserAccountResponse saveUserAccount(Boolean isOwner, Long userId, Long accountId) {
        UserAccount userAccount = new UserAccount();
        userAccount.setIsActive(true);
        userAccount.setIsOwner(isOwner);
        userAccount.setUserId(userId);
        userAccount.setAccountId(accountId);
        return saveAndFlushUserAccount.execute(userAccount);
    }

    private UserAccountResponse deactivateUserAccount(UserAccount userAccount) {
        userAccount.setIsActive(false);
        return saveAndFlushUserAccount.execute(userAccount);
    }

}
