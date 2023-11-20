package bo.jads.myfinancesbackend.app.services;

import bo.jads.myfinancesbackend.app.dto.requests.LoginRequest;
import bo.jads.myfinancesbackend.app.dto.requests.UserRequest;
import bo.jads.myfinancesbackend.app.dto.responses.LoginResponse;
import bo.jads.myfinancesbackend.app.dto.responses.UserResponse;
import bo.jads.myfinancesbackend.app.exceptions.files.FileException;
import bo.jads.myfinancesbackend.app.exceptions.files.FileReadException;
import bo.jads.myfinancesbackend.app.exceptions.users.UserException;
import bo.jads.myfinancesbackend.app.exceptions.users.UserNotFoundException;
import bo.jads.tokenmanager.exceptions.TokenGenerationException;

public interface UserService {

    UserResponse registerUser(UserRequest request) throws UserException, FileException;

    LoginResponse logIn(LoginRequest request) throws UserException, FileReadException, TokenGenerationException;

    UserResponse getUserById(Long id) throws UserNotFoundException, FileReadException;

}
