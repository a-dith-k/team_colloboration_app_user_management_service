package site.adithk.usermanagementservice.dtos;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record VerificationResponse(HttpStatus statusCode, String error, String message) {
}