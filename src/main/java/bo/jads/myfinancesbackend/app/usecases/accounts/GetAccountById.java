package bo.jads.myfinancesbackend.app.usecases.accounts;

import bo.jads.myfinancesbackend.app.domain.entities.Account;
import bo.jads.myfinancesbackend.app.domain.repositories.AccountRepository;
import bo.jads.myfinancesbackend.app.exceptions.entitynotfound.AccountNotFoundException;
import bo.jads.myfinancesbackend.app.usecases.BaseUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GetAccountById implements BaseUseCase<Long, Account> {

    private final AccountRepository accountRepository;

    @Override
    public Account execute(Long id) throws AccountNotFoundException {
        return accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
    }

}
