package site.adithk.usermanagementservice.dtos;

import jakarta.validation.constraints.*;
import lombok.Builder;

@Builder
public record UserRegistrationRequest(
                @NotBlank @NotNull @Size(min = 4) String firstName,
                @NotNull String lastName,
                @NotNull @NotBlank @Email String email,
                @NotBlank @NotNull String password,
                String verificationLink) {
}
