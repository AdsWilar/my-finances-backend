package bo.jads.myfinancesbackend.app.mappers;

import bo.jads.myfinancesbackend.app.domain.entities.Account;
import bo.jads.myfinancesbackend.app.dto.requests.AccountRequest;
import bo.jads.myfinancesbackend.app.dto.responses.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.math.BigDecimal;

@Mapper(imports = {BigDecimal.class})
public interface AccountMapper extends BaseMapper<Account, AccountRequest, AccountResponse> {

    @Mapping(target = "totalIncome", expression = "java(new BigDecimal(0))")
    @Mapping(target = "totalExpense", expression = "java(new BigDecimal(0))")
    @Mapping(target = "currentBalance", expression = "java(request.getInitialBalance())")
    @Mapping(target = "deleted", constant = "false")
    Account requestToEntity(AccountRequest request);

}
