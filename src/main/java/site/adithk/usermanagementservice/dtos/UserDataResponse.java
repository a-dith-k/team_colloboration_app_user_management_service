package site.adithk.usermanagementservice.dtos;


import jakarta.persistence.Column;
import lombok.Data;
import site.adithk.usermanagementservice.entities.UserVerificationData;
import site.adithk.usermanagementservice.enums.UserRole;

@Data
public class UserDataResponse {
    private Integer id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean isBlocked;
    private UserRole userRole;
    private UserVerificationData verificationData;
}
