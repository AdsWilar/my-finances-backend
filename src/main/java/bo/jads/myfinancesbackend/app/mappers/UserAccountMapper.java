package bo.jads.myfinancesbackend.app.mappers;

import bo.jads.myfinancesbackend.app.domain.entities.UserAccount;
import bo.jads.myfinancesbackend.app.dto.responses.UserAccountResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface UserAccountMapper extends EntityToResponseMapper<UserAccount, UserAccountResponse> {
}
