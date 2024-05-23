package site.adithk.usermanagementservice.services.user;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import site.adithk.usermanagementservice.dtos.*;
import site.adithk.usermanagementservice.entities.UserEntity;
import site.adithk.usermanagementservice.entities.UserVerificationData;
import site.adithk.usermanagementservice.enums.UserRole;
import site.adithk.usermanagementservice.exceptions.*;
import site.adithk.usermanagementservice.mappers.UserEntityMapper;
import site.adithk.usermanagementservice.repositories.UserRepository;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest registrationRequest)
            throws UserAlreadyExistsException {
        // checking for duplicate registration
        if (userRepository.findUserEntityByEmail(registrationRequest.email()).isPresent()){
            throw new UserAlreadyExistsException("User with given email already exists");
        }

        // building verification details
        UserVerificationData verificationData= UserVerificationData
                .builder()
                .isVerified(false)
                .verificationLink(registrationRequest.verificationLink())
                .generationTime(LocalDateTime.now())
                .build();

        UserEntity user=UserEntityMapper.map(registrationRequest);
        user.setIsBlocked(true);
        user.setUserRole(UserRole.APP_USER);
        user.setVerificationData(verificationData);

        UserEntity savedUser = userRepository
                .save(user);

        return modelMapper
                .map(savedUser, UserRegistrationResponse.class);
    }

    @Override
    public UserDataResponse getUserData(String email) throws UserNotFoundException {
        UserEntity user = userRepository.findUserEntityByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("user with given email does not exist"));
        return modelMapper.map(user, UserDataResponse.class);
    }


    @Override
    public List<UserDataResponse> getAllUsers() {
        List<UserDataResponse> userList=new ArrayList<>();
        userRepository
                .findAll()
                .forEach(user->userList.add(modelMapper.map(user, UserDataResponse.class)));
        return userList;
    }

    @Override
    public UserUpdateResponse updateUser(UserUpdateRequest userUpdateRequest) throws UserNotFoundException {
        UserEntity user=
                userRepository
                        .findUserEntityByEmail(userUpdateRequest.email()).orElseThrow(()->new UserNotFoundException("User Not Found"));
        modelMapper.map(userUpdateRequest,user);
        return modelMapper.map(userRepository.save(user), UserUpdateResponse.class);
    }

    @Override
    public void enableUser(String email) throws UserNotFoundException {
        UserEntity user=findUserByEmail(email);
        user.setIsBlocked(false);
        userRepository.save(user);
    }

    @Override
    public void disableUser(String email) throws UserNotFoundException {
        UserEntity user=findUserByEmail(email);
        user.setIsBlocked(true);
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String email) throws UserNotFoundException {
        UserEntity user=findUserByEmail(email);
        userRepository.delete(user);
    }




    @Override
    public UserDataResponse getUserDataByVerificationLink(String verificationString) throws InvalidLinkException {
        UserEntity user=userRepository.findUserEntityByVerificationDataVerificationLink(verificationString)
                .orElseThrow(() -> new InvalidLinkException("invalid verification link"));
        log.info("User Data from Db:{}",user);
        return modelMapper.map(user,UserDataResponse.class);
    }

    @Override
    public void updateUserByFields(String email, Boolean isVerified) throws UserNotFoundException {
        UserEntity user=findUserByEmail(email);
        user.setIsBlocked(false);
        user.getVerificationData().setIsVerified(isVerified);
        userRepository.save(user);
    }

    @Override
    public void updateUserByFields(String email, Boolean isVerified, String firstName) {

    }

    @Override
    public void updateUserByFields(String email, UserVerificationDataUpdateRequest request) throws UserNotFoundException {
        UserEntity user=findUserByEmail(email);

        UserVerificationData verificationData=modelMapper.map(request,UserVerificationData.class);
        user.setVerificationData(verificationData);

        userRepository.save(user);
    }


    @Override
    public boolean isVerified(String email) throws UserNotFoundException {
        UserEntity user=findUserByEmail(email);
        return user.getVerificationData().getIsVerified();
    }

    private UserEntity findUserByEmail(String email) throws UserNotFoundException {
        return
                userRepository
                        .findUserEntityByEmail(email)
                        .orElseThrow(()->new UserNotFoundException("User Not Found"));
    }





}
