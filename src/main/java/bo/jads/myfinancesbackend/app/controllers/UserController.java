package bo.jads.myfinancesbackend.app.controllers;

import bo.jads.myfinancesbackend.app.dto.requests.LoginRequest;
import bo.jads.myfinancesbackend.app.dto.requests.UserRequest;
import bo.jads.myfinancesbackend.app.dto.responses.GeneralResponse;
import bo.jads.myfinancesbackend.app.dto.responses.LoginResponse;
import bo.jads.myfinancesbackend.app.dto.responses.UserResponse;
import bo.jads.myfinancesbackend.app.dto.responses.enums.ResponseTitle;
import bo.jads.myfinancesbackend.app.exceptions.files.FileReadException;
import bo.jads.myfinancesbackend.app.exceptions.files.FileException;
import bo.jads.myfinancesbackend.app.exceptions.users.UserException;
import bo.jads.myfinancesbackend.app.exceptions.users.UserNotFoundException;
import bo.jads.myfinancesbackend.app.services.UserService;
import bo.jads.tokenmanager.exceptions.TokenGenerationException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public GeneralResponse<UserResponse> registerUser(@Valid @RequestBody UserRequest request) throws UserException,
            FileException {
        UserResponse response = userService.registerUser(request);
        return new GeneralResponse<>(
                ResponseTitle.USERS, true, "User registered successfully.", response
        );
    }

    @PostMapping("/log-in")
    public GeneralResponse<LoginResponse> logIn(@Valid @RequestBody LoginRequest request) throws UserException,
            TokenGenerationException {
        LoginResponse response = userService.logIn(request);
        return new GeneralResponse<>(ResponseTitle.USERS, true, "Successful login.", response);
    }

    @GetMapping("/{id}")
    public GeneralResponse<UserResponse> getUserById(@PathVariable("id") Long id) throws UserNotFoundException,
            FileReadException {
        UserResponse response = userService.getUserById(id);
        return new GeneralResponse<>(ResponseTitle.USERS, true, "User obtained successfully.", response);
    }

}
