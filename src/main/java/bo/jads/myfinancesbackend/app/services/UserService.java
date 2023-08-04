package bo.jads.myfinancesbackend.app.services;

import bo.jads.myfinancesbackend.app.dto.requests.UserRequest;
import bo.jads.myfinancesbackend.app.dto.responses.UserResponse;
import bo.jads.myfinancesbackend.app.exceptions.DirectoryCreationException;
import bo.jads.myfinancesbackend.app.exceptions.FileReadException;
import bo.jads.myfinancesbackend.app.exceptions.InvalidPasswordException;
import bo.jads.myfinancesbackend.app.exceptions.SaveFileException;
import bo.jads.myfinancesbackend.app.exceptions.users.UserException;
import bo.jads.myfinancesbackend.app.exceptions.users.UserNotFoundException;

public interface UserService {

    UserResponse registerUser(UserRequest request) throws UserException, InvalidPasswordException,
            DirectoryCreationException, SaveFileException;

    UserResponse getUserByUserId(Long userId) throws UserNotFoundException, FileReadException;

}
