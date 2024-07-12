package site.adithk.usermanagementservice.dtos;


import jakarta.persistence.Column;
import lombok.Data;
import site.adithk.usermanagementservice.entities.UserVerificationData;
import site.adithk.usermanagementservice.enums.UserRole;

import java.util.Date;

@Data
public class UserDataResponse {
    private Integer id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Boolean isBlocked;
    private UserRole userRole;
    private String profileImageUrl;
    private Date joinDate;
    private String jobTitle;
    private String aboutMe;
    private String department;
    private Integer teamId;
    private Boolean isOnline;
    private UserVerificationData verificationData;

}
