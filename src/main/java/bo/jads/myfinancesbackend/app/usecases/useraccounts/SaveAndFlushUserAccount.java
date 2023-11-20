package bo.jads.myfinancesbackend.app.usecases.useraccounts;

import bo.jads.myfinancesbackend.app.domain.entities.UserAccount;
import bo.jads.myfinancesbackend.app.domain.repositories.UserAccountRepository;
import bo.jads.myfinancesbackend.app.dto.responses.UserAccountResponse;
import bo.jads.myfinancesbackend.app.mappers.UserAccountMapper;
import bo.jads.myfinancesbackend.app.usecases.BaseUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SaveAndFlushUserAccount implements BaseUseCase<UserAccount, UserAccountResponse> {

    private final UserAccountRepository userAccountRepository;
    private final UserAccountMapper userAccountMapper;

    @Override
    public UserAccountResponse execute(UserAccount userAccount) {
        UserAccount processedUserAccount = userAccountRepository.saveAndFlush(userAccount);
        return userAccountMapper.entityToResponse(processedUserAccount);
    }

}
