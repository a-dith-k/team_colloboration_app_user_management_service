package site.adithk.usermanagementservice.controllers;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.adithk.usermanagementservice.dtos.*;
import site.adithk.usermanagementservice.entities.UserVerificationData;
import site.adithk.usermanagementservice.exceptions.*;
import site.adithk.usermanagementservice.services.user.UserService;

import java.util.List;

@RestController
@RequestMapping("users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("")
    public ResponseEntity<List<UserDataResponse>> getAllUsers(){

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("by-verification-link")
    public ResponseEntity<UserDataResponse> getUserDataByVerificationString(@RequestParam("verification-string") String verificationString)
            throws  InvalidLinkException {

        return ResponseEntity.ok(userService.getUserDataByVerificationLink(verificationString));
    }

    @GetMapping("{email}")
    public ResponseEntity<UserDataResponse> getUserData(@PathVariable("email") String email)
            throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUserData(email));
    }

    //end point to register new user
    @PostMapping("")
    public ResponseEntity<UserRegistrationResponse> registerUser(
            @Valid @RequestBody UserRegistrationRequest registrationRequest) throws UserAlreadyExistsException {

        return ResponseEntity.ok(userService.registerUser(registrationRequest));
    }

    @PutMapping("update")
    public ResponseEntity<UserUpdateResponse> updateUser(
            @Valid @RequestBody UserUpdateRequest userUpdateRequest) throws UserNotFoundException {

        return ResponseEntity.ok(userService.updateUser(userUpdateRequest));
    }

    @PutMapping("enable/{email}")
    public ResponseEntity<Void> enableUser(@PathVariable("email") String email) throws UserNotFoundException {

        userService.enableUser(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("disable/{email}")
    public ResponseEntity<Void> disableUser(@PathVariable("email") String email) throws UserNotFoundException {

        userService.disableUser(email);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteUser(@RequestParam("email") String email) throws UserNotFoundException{
        userService.deleteUser(email);
        return ResponseEntity.ok().build();
    }

    @PutMapping("update-by-fields")
    public ResponseEntity<Void> updateUserPartially(@RequestParam("email") String email, @RequestParam("is-verified")Boolean isVerified) throws UserNotFoundException {

        userService.updateUserByFields(email,isVerified);
        return ResponseEntity.ok().build();
    }

    @PutMapping("verification-data")
    public ResponseEntity<Void> updateVerificationData(@RequestParam("email") String email,@RequestBody UserVerificationDataUpdateRequest request) throws UserNotFoundException {

        userService.updateUserByFields(email,request);
        return ResponseEntity.ok().build();
    }





}
