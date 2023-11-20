package bo.jads.myfinancesbackend.app.usecases.useraccounts;

import bo.jads.myfinancesbackend.app.domain.entities.UserAccount;
import bo.jads.myfinancesbackend.app.domain.repositories.UserAccountRepository;
import bo.jads.myfinancesbackend.app.dto.responses.UserAccountResponse;
import bo.jads.myfinancesbackend.app.mappers.UserAccountMapper;
import bo.jads.myfinancesbackend.app.usecases.BaseUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class SaveAndFlushUserAccounts implements BaseUseCase<List<UserAccount>, List<UserAccountResponse>> {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper userAccountMapper;

    @Override
    public List<UserAccountResponse> execute(List<UserAccount> userAccounts) {
        List<UserAccount> savedUserAccounts = userAccountRepository.saveAllAndFlush(userAccounts);
        return userAccountMapper.entitiesToResponses(savedUserAccounts);
    }

}
