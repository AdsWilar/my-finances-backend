package bo.jads.myfinancesbackend.app.services.impl;

import bo.jads.myfinancesbackend.app.configs.FileStorageConfig;
import bo.jads.myfinancesbackend.app.domain.entities.User;
import bo.jads.myfinancesbackend.app.domain.entities.enums.UserStatus;
import bo.jads.myfinancesbackend.app.dto.FileDto;
import bo.jads.myfinancesbackend.app.dto.requests.LoginRequest;
import bo.jads.myfinancesbackend.app.dto.requests.UserRequest;
import bo.jads.myfinancesbackend.app.dto.responses.LoginResponse;
import bo.jads.myfinancesbackend.app.dto.responses.UserResponse;
import bo.jads.myfinancesbackend.app.exceptions.files.FileException;
import bo.jads.myfinancesbackend.app.exceptions.files.FileReadException;
import bo.jads.myfinancesbackend.app.exceptions.users.*;
import bo.jads.myfinancesbackend.app.mappers.UserMapper;
import bo.jads.myfinancesbackend.app.services.UserService;
import bo.jads.myfinancesbackend.app.usecases.users.GetUserByEmail;
import bo.jads.myfinancesbackend.app.usecases.users.GetUserById;
import bo.jads.myfinancesbackend.app.usecases.users.GetUserByUsername;
import bo.jads.myfinancesbackend.app.usecases.users.SaveUser;
import bo.jads.myfinancesbackend.app.utilities.Base64Utility;
import bo.jads.myfinancesbackend.app.utilities.IoUtility;
import bo.jads.myfinancesbackend.app.utilities.PasswordEncryptor;
import bo.jads.myfinancesbackend.app.utilities.filesaver.UserPhotoFileManager;
import bo.jads.tokenmanager.core.TokenManager;
import bo.jads.tokenmanager.dto.TokenRequest;
import bo.jads.tokenmanager.dto.TokenResponse;
import bo.jads.tokenmanager.enums.ExpirationTimeType;
import bo.jads.tokenmanager.exceptions.TokenGenerationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final GetUserByUsername getUserByUsername;
    private final GetUserByEmail getUserByEmail;
    private final SaveUser saveUser;
    private final GetUserById getUserById;

    private final UserMapper userMapper;
    private final FileStorageConfig fileStorageConfig;
    private final UserPhotoFileManager fileManager;

    @Override
    public UserResponse registerUser(UserRequest request) throws UserException, FileException {
        String username = request.getUsername();
        try {
            getUserByUsername.execute(username);
            throw new UserAlreadyRegisteredException(
                    String.format("A user with the username: %s is already registered.", username)
            );
        } catch (UserNotFoundException ignored) {
            if (!request.getPassword().equals(request.getPasswordConfirmation())) {
                throw new InvalidPasswordException("Passwords do not match.");
            }
            String email = request.getEmail();
            try {
                getUserByEmail.execute(email);
                throw new UserAlreadyRegisteredException(
                        String.format("A user with the email: %s is already registered.", email)
                );
            } catch (UserNotFoundException ignored1) {
                return saveUser.execute(request);
            }
        }
    }

    @Override
    public LoginResponse logIn(LoginRequest request) throws UserException, TokenGenerationException {
        try {
            User user = getUserByUsername.execute(request.getUsername());
            if (!PasswordEncryptor.checkPassword(request.getPassword(), user.getPassword())) {
                throw new InvalidCredentialsException();
            }
            if (user.getStatus().equals(UserStatus.DISABLED)) {
                throw new DisabledUserException();
            }
            UserResponse userResponse = userMapper.fromUserToUserResponse(user);
            TokenResponse tokenResponse = generateToken(userResponse);
            return new LoginResponse(userResponse, tokenResponse.getAccessToken(), tokenResponse.getExpirationTime());
        } catch (UserNotFoundException e) {
            throw new InvalidCredentialsException();
        }
    }

    @Override
    public UserResponse getUserById(Long id) throws UserNotFoundException, FileReadException {
        User user = getUserById.execute(id);
        UserResponse response = userMapper.fromUserToUserResponse(user);
        String photoPath = user.getPhotoPath();
        if (photoPath != null && !photoPath.isBlank()) {
            setUserPhoto(photoPath, response);
        }
        return response;
    }

    private TokenResponse generateToken(UserResponse userResponse) throws TokenGenerationException {
        TokenRequest<UserResponse> tokenRequest = new TokenRequest<>(
                fileStorageConfig.getPrivateKeyAbsolutePath(),
                fileStorageConfig.getPublicKeyAbsolutePath(),
                false,
                userResponse.getUsername(),
                ExpirationTimeType.MINUTE,
                30,
                userResponse
        );
        return TokenManager.getInstance().generateToken(tokenRequest);
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
            throw new FileReadException("User photo could not be found.", e);
        }
    }

}
