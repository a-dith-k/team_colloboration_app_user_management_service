package site.adithk.usermanagementservice.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import site.adithk.usermanagementservice.UserRepository;
import site.adithk.usermanagementservice.dtos.UserRegistrationRequest;
import site.adithk.usermanagementservice.dtos.UserRegistrationResponse;
import site.adithk.usermanagementservice.entities.UserEntity;
import site.adithk.usermanagementservice.mappers.UserEntityMapper;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest registrationRequest) {

        UserEntity savedUser
                = userRepository
                .save(UserEntityMapper.map(registrationRequest));
        return modelMapper
                .map(savedUser, UserRegistrationResponse.class);
    }

}
