package bo.jads.myfinancesbackend.app.services.impl;

import bo.jads.myfinancesbackend.app.dto.responses.AccountActionResponse;
import bo.jads.myfinancesbackend.app.services.AccountActionService;
import bo.jads.myfinancesbackend.app.usecases.accountactions.GetAllAccountActions;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AccountActionServiceImpl implements AccountActionService {

    private final GetAllAccountActions getAllAccountActions;

    @Override
    public List<AccountActionResponse> getAllAccountActions() {
        return getAllAccountActions.execute();
    }

}
