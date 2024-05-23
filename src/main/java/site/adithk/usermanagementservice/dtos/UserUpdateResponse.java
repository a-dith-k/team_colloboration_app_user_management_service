package site.adithk.usermanagementservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateResponse {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
