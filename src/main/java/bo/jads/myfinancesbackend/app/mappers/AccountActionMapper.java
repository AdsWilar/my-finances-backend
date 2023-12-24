package bo.jads.myfinancesbackend.app.mappers;

import bo.jads.myfinancesbackend.app.domain.entities.AccountAction;
import bo.jads.myfinancesbackend.app.dto.responses.AccountActionResponse;
import org.mapstruct.Mapper;

@Mapper
public interface AccountActionMapper extends EntityToResponseMapper<AccountAction, AccountActionResponse> {
}
