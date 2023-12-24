package bo.jads.myfinancesbackend.app.services;

import bo.jads.myfinancesbackend.app.dto.responses.AccountActionResponse;

import java.util.List;

public interface AccountActionService {

    List<AccountActionResponse> getAllAccountActions();

}
