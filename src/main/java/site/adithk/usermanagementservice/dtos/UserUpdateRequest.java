package site.adithk.usermanagementservice.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserUpdateRequest(
    @NotBlank @NotNull @Size(min = 4) String firstName,
    @NotNull String lastName,
    @NotNull @NotBlank @Email String email,
    @NotBlank @NotNull String password) { }
