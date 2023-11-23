package bo.jads.myfinancesbackend.app.usecases.users;

import bo.jads.myfinancesbackend.app.domain.entities.User;
import bo.jads.myfinancesbackend.app.domain.repositories.UserRepository;
import bo.jads.myfinancesbackend.app.exceptions.entitynotfound.UserNotFoundException;
import bo.jads.myfinancesbackend.app.usecases.BaseUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class GetUserById implements BaseUseCase<Long, User> {

    private final UserRepository userRepository;

    @Override
    public User execute(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

}
