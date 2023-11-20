package bo.jads.myfinancesbackend.app.usecases.useraccounts;

import bo.jads.myfinancesbackend.app.domain.entities.UserAccount;
import bo.jads.myfinancesbackend.app.domain.repositories.UserAccountRepository;
import bo.jads.myfinancesbackend.app.exceptions.useraccounts.UserAccountNotFoundException;
import bo.jads.myfinancesbackend.app.usecases.BaseUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GetUserAccountById implements BaseUseCase<Long, UserAccount> {

    private final UserAccountRepository userAccountRepository;

    @Override
    public UserAccount execute(Long id) throws UserAccountNotFoundException {
        return userAccountRepository.findById(id).orElseThrow(UserAccountNotFoundException::new);
    }

}
