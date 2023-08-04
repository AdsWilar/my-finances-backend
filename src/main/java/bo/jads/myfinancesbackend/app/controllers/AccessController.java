package bo.jads.myfinancesbackend.app.controllers;

import bo.jads.myfinancesbackend.app.dto.requests.AccessRequest;
import bo.jads.myfinancesbackend.app.dto.responses.AccessResponse;
import bo.jads.myfinancesbackend.app.dto.responses.GeneralResponse;
import bo.jads.myfinancesbackend.app.exceptions.users.UserNotFoundException;
import bo.jads.myfinancesbackend.app.services.AccessService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/access")
public class AccessController {

    private final AccessService accessService;

    @PostMapping("/log-in")
    public GeneralResponse<AccessResponse> logIn(
            @Valid @RequestBody AccessRequest request
    ) throws UserNotFoundException {
        AccessResponse response = accessService.logIn(request);
        return new GeneralResponse<>("", true, "", response);
    }

}
