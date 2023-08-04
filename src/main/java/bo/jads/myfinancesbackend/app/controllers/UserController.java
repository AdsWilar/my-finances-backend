package bo.jads.myfinancesbackend.app.controllers;

import bo.jads.myfinancesbackend.app.dto.requests.UserRequest;
import bo.jads.myfinancesbackend.app.dto.responses.GeneralResponse;
import bo.jads.myfinancesbackend.app.dto.responses.UserResponse;
import bo.jads.myfinancesbackend.app.exceptions.DirectoryCreationException;
import bo.jads.myfinancesbackend.app.exceptions.FileReadException;
import bo.jads.myfinancesbackend.app.exceptions.InvalidPasswordException;
import bo.jads.myfinancesbackend.app.exceptions.SaveFileException;
import bo.jads.myfinancesbackend.app.exceptions.users.UserException;
import bo.jads.myfinancesbackend.app.exceptions.users.UserNotFoundException;
import bo.jads.myfinancesbackend.app.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public GeneralResponse<UserResponse> registerUser(@Valid @RequestBody UserRequest request)
            throws UserException, InvalidPasswordException, DirectoryCreationException, SaveFileException {
        UserResponse response = userService.registerUser(request);
        return new GeneralResponse<>("", true, "", response);
    }

    @GetMapping("/{userId}")
    public GeneralResponse<UserResponse> getUserByUserId(@PathVariable("userId") Long userId)
            throws UserNotFoundException, FileReadException {
        UserResponse response = userService.getUserByUserId(userId);
        return new GeneralResponse<>("", true, "", response);
    }

}
