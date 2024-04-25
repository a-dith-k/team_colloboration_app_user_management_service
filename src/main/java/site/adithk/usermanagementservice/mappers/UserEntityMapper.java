package site.adithk.usermanagementservice.mappers;

import site.adithk.usermanagementservice.dtos.UserRegistrationRequest;
import site.adithk.usermanagementservice.entities.UserEntity;

public class UserEntityMapper {

    public static UserEntity map(UserRegistrationRequest registrationRequest){

        return new UserEntity(registrationRequest.email(),registrationRequest.password(),registrationRequest.firstName(),registrationRequest.lastName());
    }
}
