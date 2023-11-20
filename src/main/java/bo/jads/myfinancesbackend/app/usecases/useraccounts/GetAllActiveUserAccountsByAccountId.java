package bo.jads.myfinancesbackend.app.usecases.useraccounts;

import bo.jads.myfinancesbackend.app.domain.entities.UserAccount;
import bo.jads.myfinancesbackend.app.domain.repositories.UserAccountRepository;
import bo.jads.myfinancesbackend.app.usecases.BaseUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class GetAllActiveUserAccountsByAccountId implements BaseUseCase<Long, List<UserAccount>> {

    private final UserAccountRepository userAccountRepository;

    @Override
    public List<UserAccount> execute(Long accountId) {
        return userAccountRepository.getAllByIsActiveTrueAndAccountId(accountId);
    }

}
