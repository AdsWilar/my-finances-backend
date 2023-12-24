package bo.jads.myfinancesbackend.app.usecases.accountactions;

import bo.jads.myfinancesbackend.app.domain.entities.AccountAction;
import bo.jads.myfinancesbackend.app.domain.repositories.AccountActionRepository;
import bo.jads.myfinancesbackend.app.dto.responses.AccountActionResponse;
import bo.jads.myfinancesbackend.app.mappers.AccountActionMapper;
import bo.jads.myfinancesbackend.app.usecases.GetAllUseCase;
import org.springframework.stereotype.Component;

@Component
public class GetAllAccountActions extends GetAllUseCase<
        AccountAction, AccountActionRepository, AccountActionResponse, AccountActionMapper
        > {

    public GetAllAccountActions(AccountActionRepository repository, AccountActionMapper mapper) {
        super(repository, mapper);
    }

}
