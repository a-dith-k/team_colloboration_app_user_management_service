package site.adithk.usermanagementservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import site.adithk.usermanagementservice.dtos.*;
import site.adithk.usermanagementservice.entities.UserEntity;
import site.adithk.usermanagementservice.exceptions.UserAlreadyExistsException;
import site.adithk.usermanagementservice.exceptions.UserNotFoundException;
import site.adithk.usermanagementservice.helper.LinkGenerator;
import site.adithk.usermanagementservice.mappers.UserEntityMapper;
import site.adithk.usermanagementservice.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements site.adithk.usermanagementservice.services.UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final EmailSenderService emailSenderService;
    private final EmailService emailService;

    private final String domainUrl;

    public UserServiceImpl(UserRepository userRepository,
                           ModelMapper modelMapper, EmailSenderService emailSenderService, EmailService emailService, @Value("${app.frontend.domain.name}") String domainUrl) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.emailSenderService = emailSenderService;
        this.emailService = emailService;
        this.domainUrl = domainUrl;
    }

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest registrationRequest)
            throws UserAlreadyExistsException {
        // checking for duplicate registration
        if (userRepository.findUserEntityByEmail(registrationRequest.email()).isPresent())
            throw new UserAlreadyExistsException("User with given email already exists");


        // saving user to the database
        UserEntity savedUser = userRepository
                .save(UserEntityMapper.map(registrationRequest));
            
        // sending email for confirmation
        String verificationLink=LinkGenerator.getVerificationLink();
        emailSenderService.sendSimpleEmail(registrationRequest.email(), "One App Verfication Link",
                domainUrl.concat(verificationLink));
        // saving verification details
        emailService
            .saveVerificationDetails(
                VerificationDataRequestDto.builder()
                .email(registrationRequest.email())
                .verificationLink(verificationLink)
                .build()
                );
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
    public void unBlockUser(String email) throws UserNotFoundException {
        UserEntity user
                =userRepository.findUserEntityByEmail(email).orElseThrow(()->new UserNotFoundException("user not found"));

        user.setIsBlocked(false);
        userRepository.save(user);
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

    private UserEntity findUserByEmail(String email) throws UserNotFoundException {
        return
                userRepository
                        .findUserEntityByEmail(email)
                        .orElseThrow(()->new UserNotFoundException("User Not Found"));
    }

}
