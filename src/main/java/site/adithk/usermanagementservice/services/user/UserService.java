package site.adithk.usermanagementservice.services.user;

import site.adithk.usermanagementservice.dtos.*;
import site.adithk.usermanagementservice.exceptions.*;

import java.util.List;


public interface UserService {

    UserRegistrationResponse registerUser(UserRegistrationRequest registrationRequest) throws UserAlreadyExistsException;

    UserDataResponse getUserData(String email) throws UserNotFoundException;

    List<UserDataResponse>getAllUsers();

    UserUpdateResponse updateUser(UserUpdateRequest userUpdateRequest) throws UserNotFoundException;

    void enableUser(String email) throws UserNotFoundException;

    void disableUser(String email) throws UserNotFoundException;

    void deleteUser(String email) throws UserNotFoundException;


    boolean isVerified(String email) throws UserNotFoundException;


    UserDataResponse getUserDataByVerificationLink(String email) throws InvalidLinkException;

    void updateUserByFields(String email,Boolean isVerified) throws UserNotFoundException;

    void updateUserByFields(String email, Boolean isVerified,String firstName);

    void updateUserByFields(String email, UserVerificationDataUpdateRequest request) throws UserNotFoundException;
}
