package bo.jads.myfinancesbackend.app.usecases.useraccounts;

import bo.jads.myfinancesbackend.app.domain.entities.UserAccount;
import bo.jads.myfinancesbackend.app.domain.repositories.UserAccountRepository;
import bo.jads.myfinancesbackend.app.exceptions.entitynotfound.UserAccountNotFoundException;
import bo.jads.myfinancesbackend.app.usecases.BaseUseCase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GetUserAccountByUserIdAndAccountId
        implements BaseUseCase<GetUserAccountByUserIdAndAccountId.Param, UserAccount> {

    private final UserAccountRepository userAccountRepository;

    @Override
    public UserAccount execute(Param param) throws UserAccountNotFoundException {
        return userAccountRepository.findByUserIdAndAccountId(param.getUserId(), param.getAccountId())
                .orElseThrow(UserAccountNotFoundException::new);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Param {

        private Long userId;
        private Long accountId;

    }

}
