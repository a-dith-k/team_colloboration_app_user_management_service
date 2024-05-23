package site.adithk.usermanagementservice.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserVerificationDataUpdateRequest {

    private String verificationLink;

    private Boolean isVerified;

    private LocalDateTime generationTime;
}
