package bo.jads.myfinancesbackend.app.mappers;

import bo.jads.myfinancesbackend.app.domain.entities.User;
import bo.jads.myfinancesbackend.app.domain.entities.enums.UserStatus;
import bo.jads.myfinancesbackend.app.dto.requests.UserRequest;
import bo.jads.myfinancesbackend.app.dto.responses.UserResponse;
import bo.jads.myfinancesbackend.app.utilities.PasswordEncryptor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(imports = {PasswordEncryptor.class, UserStatus.class})
public interface UserMapper extends BaseMapper<User, UserRequest, UserResponse> {

    @Mapping(target = "password", expression = "java(PasswordEncryptor.hashPassword(request.getPassword()))")
    @Mapping(target = "status", expression = "java(UserStatus.ENABLED)")
    User requestToEntity(UserRequest request);

}
