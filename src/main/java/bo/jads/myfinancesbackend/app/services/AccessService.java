package bo.jads.myfinancesbackend.app.services;

import bo.jads.myfinancesbackend.app.dto.requests.AccessRequest;
import bo.jads.myfinancesbackend.app.dto.responses.AccessResponse;
import bo.jads.myfinancesbackend.app.exceptions.users.UserNotFoundException;

public interface AccessService {

    AccessResponse logIn(AccessRequest request) throws UserNotFoundException;

}
