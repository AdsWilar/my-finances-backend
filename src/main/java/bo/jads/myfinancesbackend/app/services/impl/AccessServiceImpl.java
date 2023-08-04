package bo.jads.myfinancesbackend.app.services.impl;

import bo.jads.myfinancesbackend.app.domain.entities.User;
import bo.jads.myfinancesbackend.app.dto.requests.AccessRequest;
import bo.jads.myfinancesbackend.app.dto.responses.AccessResponse;
import bo.jads.myfinancesbackend.app.exceptions.users.UserNotFoundException;
import bo.jads.myfinancesbackend.app.services.AccessService;
import bo.jads.myfinancesbackend.app.usecases.users.GetUserByUsername;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AccessServiceImpl implements AccessService {

    private final GetUserByUsername getUserByUsername;

    @Override
    public AccessResponse logIn(AccessRequest request) throws UserNotFoundException {
        User user = getUserByUsername.execute(request.getUsername());

        return null;
    }

}
