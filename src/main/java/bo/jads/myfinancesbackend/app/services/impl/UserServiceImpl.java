package bo.jads.myfinancesbackend.app.services.impl;

import bo.jads.myfinancesbackend.app.domain.entities.User;
import bo.jads.myfinancesbackend.app.dto.FileDto;
import bo.jads.myfinancesbackend.app.dto.requests.UserRequest;
import bo.jads.myfinancesbackend.app.dto.responses.UserResponse;
import bo.jads.myfinancesbackend.app.exceptions.*;
import bo.jads.myfinancesbackend.app.exceptions.users.UserAlreadyRegisteredException;
import bo.jads.myfinancesbackend.app.exceptions.users.UserEmailAlreadyRegisteredException;
import bo.jads.myfinancesbackend.app.exceptions.users.UserException;
import bo.jads.myfinancesbackend.app.exceptions.users.UserNotFoundException;
import bo.jads.myfinancesbackend.app.mappers.UserMapper;
import bo.jads.myfinancesbackend.app.services.UserService;
import bo.jads.myfinancesbackend.app.usecases.users.GetUserByEmail;
import bo.jads.myfinancesbackend.app.usecases.users.GetUserById;
import bo.jads.myfinancesbackend.app.usecases.users.GetUserByUsername;
import bo.jads.myfinancesbackend.app.usecases.users.SaveUser;
import bo.jads.myfinancesbackend.app.utilities.Base64Utility;
import bo.jads.myfinancesbackend.app.utilities.BaseUtility;
import bo.jads.myfinancesbackend.app.utilities.IoUtility;
import bo.jads.myfinancesbackend.app.utilities.filesaver.UserPhotoFileManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final GetUserByUsername getUserByUsername;
    private final GetUserByEmail getUserByEmail;
    private final SaveUser saveUser;
    private final GetUserById getUserById;

    private final UserMapper userMapper;
    private final UserPhotoFileManager fileManager;

    @Override
    public UserResponse registerUser(
            UserRequest request
    ) throws UserException, InvalidPasswordException, DirectoryCreationException, SaveFileException {
        try {
            getUserByUsername.execute(request.getUsername());
            throw new UserAlreadyRegisteredException();
        } catch (UserNotFoundException ignored) {
            if (!request.getPassword().equals(request.getPasswordConfirmation())) {
                throw new InvalidPasswordException("Passwords do not match");
            }
            String email = request.getEmail().toLowerCase();
            try {
                getUserByEmail.execute(email);
                throw new UserEmailAlreadyRegisteredException();
            } catch (UserNotFoundException ignored1) {
                return saveUser.execute(request);
            }
        }
    }

    @Override
    public UserResponse getUserByUserId(Long userId) throws UserNotFoundException, FileReadException {
        User user = getUserById.execute(userId);
        UserResponse response = userMapper.fromUserToUserResponse(user);
        String photoPath = user.getPhoto();
        if (!BaseUtility.isNull(photoPath) && !photoPath.isBlank()) {
            setUserPhoto(photoPath, response);
        }
        return response;
    }

    private void verifyUserEmail(String email) throws InvalidEmailException {
        if (BaseUtility.isNull(email) || email.isBlank()) {
            return;
        }
        Pattern pattern = Pattern.compile("([a-z0-9]+(\\.?[a-z0-9])*)+@(([a-z]+)\\.([a-z]+))+");
        Matcher matcher = pattern.matcher(email);
        if (matcher.find()) {
            return;
        }
        throw new InvalidEmailException("The email does not meet a valid format");
    }

    private void setUserPhoto(String photoPath, UserResponse response) throws FileReadException {
        String photoAbsolutePath = fileManager.getAbsolutePath(photoPath);
        File photoFile = new File(photoAbsolutePath);
        try {
            byte[] photoBytes = IoUtility.readFile(photoAbsolutePath);
            FileDto photo = new FileDto(
                    IoUtility.getFileNameWithoutExtension(photoFile),
                    Base64Utility.encodeAsString(photoBytes),
                    IoUtility.getFileExtension(photoFile)
            );
            response.setPhoto(photo);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileReadException("User photo could not be found.", e);
        }
    }

}
