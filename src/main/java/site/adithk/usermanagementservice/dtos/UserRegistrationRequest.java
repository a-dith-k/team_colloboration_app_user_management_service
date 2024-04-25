package site.adithk.usermanagementservice.dtos;

import jakarta.validation.constraints.*;

public record UserRegistrationRequest(
        @NotBlank @NotNull @Size(min = 4) String firstName,
        @NotBlank @NotNull  String lastName,
        @NotNull @NotBlank @Email String email,
        @NotBlank @NotNull
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{4,12}$",
                message = "password must be min 4 and max 12 length containing at least 1 uppercase, 1 lowercase, 1 special character and 1 digit ")
        String password) {
}
