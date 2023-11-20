package bo.jads.myfinancesbackend.app.usecases.accounts;

import bo.jads.myfinancesbackend.app.domain.entities.Account;
import bo.jads.myfinancesbackend.app.domain.repositories.AccountRepository;
import bo.jads.myfinancesbackend.app.dto.requests.AccountRequest;
import bo.jads.myfinancesbackend.app.dto.responses.AccountResponse;
import bo.jads.myfinancesbackend.app.mappers.AccountMapper;
import bo.jads.myfinancesbackend.app.usecases.BaseUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SaveAccount implements BaseUseCase<AccountRequest, AccountResponse> {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountResponse execute(AccountRequest request) {
        Account account = accountMapper.requestToEntity(request);
        Account savedAccount = accountRepository.saveAndFlush(account);
        return accountMapper.entityToResponse(savedAccount);
    }

}
