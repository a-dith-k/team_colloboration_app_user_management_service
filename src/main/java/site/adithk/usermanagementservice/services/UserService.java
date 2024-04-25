package site.adithk.usermanagementservice.services;

import org.springframework.stereotype.Service;
import site.adithk.usermanagementservice.dtos.UserRegistrationRequest;
import site.adithk.usermanagementservice.dtos.UserRegistrationResponse;


public interface UserService {

    UserRegistrationResponse registerUser(UserRegistrationRequest registrationRequest);
}
