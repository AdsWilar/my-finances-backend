package bo.jads.myfinancesbackend.app.usecases.users;

import bo.jads.myfinancesbackend.app.domain.entities.User;
import bo.jads.myfinancesbackend.app.domain.repositories.UserRepository;
import bo.jads.myfinancesbackend.app.dto.FileDto;
import bo.jads.myfinancesbackend.app.dto.requests.UserRequest;
import bo.jads.myfinancesbackend.app.dto.responses.UserResponse;
import bo.jads.myfinancesbackend.app.exceptions.files.FileException;
import bo.jads.myfinancesbackend.app.mappers.UserMapper;
import bo.jads.myfinancesbackend.app.usecases.BaseUseCase;
import bo.jads.myfinancesbackend.app.utilities.filesaver.UserPhotoFileManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class SaveUser implements BaseUseCase<UserRequest, UserResponse> {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserPhotoFileManager fileManager;

    @Override
    public UserResponse execute(UserRequest request) throws FileException {
        User user = userMapper.fromUserRequestToUser(request);
        User savedUser = userRepository.saveAndFlush(user);
        savedUser.setCode(savedUser.getUsername().concat("#").concat(savedUser.getId().toString()));
        FileDto photo = request.getPhoto();
        if (photo != null) {
            String photoPath = fileManager.savePhoto(savedUser.getId(), photo);
            savedUser.setPhotoPath(photoPath);
        }
        User updatedUser = userRepository.saveAndFlush(savedUser);
        UserResponse response = userMapper.fromUserToUserResponse(updatedUser);
        response.setPhoto(photo);
        return response;
    }

}
