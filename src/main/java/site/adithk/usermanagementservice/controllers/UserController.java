package site.adithk.usermanagementservice.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.adithk.usermanagementservice.dtos.UserRegistrationRequest;
import site.adithk.usermanagementservice.dtos.UserRegistrationResponse;
import site.adithk.usermanagementservice.services.UserService;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserRegistrationResponse> registerUser(@Valid @RequestBody UserRegistrationRequest registrationRequest){

        return ResponseEntity.ok(userService.registerUser(registrationRequest));
    }
}
