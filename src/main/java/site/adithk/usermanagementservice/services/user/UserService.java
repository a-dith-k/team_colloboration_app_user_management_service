package site.adithk.usermanagementservice.services;

import site.adithk.usermanagementservice.dtos.*;
import site.adithk.usermanagementservice.exceptions.UserAlreadyExistsException;
import site.adithk.usermanagementservice.exceptions.UserNotFoundException;

import java.util.List;


public interface UserService {

    UserRegistrationResponse registerUser(UserRegistrationRequest registrationRequest) throws UserAlreadyExistsException;

    UserDataResponse getUserData(String email) throws UserNotFoundException;

    void unBlockUser(String email) throws UserNotFoundException;

    List<UserDataResponse>getAllUsers();

    UserUpdateResponse updateUser(UserUpdateRequest userUpdateRequest) throws UserNotFoundException;

    void enableUser(String email) throws UserNotFoundException;

    void disableUser(String email) throws UserNotFoundException;

    void deleteUser(String email) throws UserNotFoundException;
}
