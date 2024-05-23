package site.adithk.usermanagementservice.exceptionhandlers;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import site.adithk.usermanagementservice.dtos.VerificationResponse;
import site.adithk.usermanagementservice.exceptions.*;

@RestControllerAdvice
@Slf4j
public class UserControllerAdvice {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<String> userAlreadyExists(UserAlreadyExistsException ex) {
        log.info("User Already Exist Exception:{}",ex.getMessage());
        return new ResponseEntity<>("User AlreadyExists", HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> userNotFound(UserNotFoundException ex) {

        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VerificationLinkExpiredException.class)
    public ResponseEntity<VerificationResponse> verificationLinkExpired(VerificationLinkExpiredException ex) {

        return new ResponseEntity<>(
                VerificationResponse
                        .builder()
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .error("expired")
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AlreadyVerifiedException.class)
    public ResponseEntity<VerificationResponse> alreadyVerifiedException(AlreadyVerifiedException ex) {

        return new ResponseEntity<>(
                VerificationResponse
                        .builder()
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .error("verified")
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidLinkException.class)
    public ResponseEntity<VerificationResponse> invalidVerificationLink(InvalidLinkException ex) {

        return new ResponseEntity<>(
                VerificationResponse
                        .builder()
                        .statusCode(HttpStatus.BAD_REQUEST)
                        .error("invalid")
                        .message(ex.getMessage())
                        .build(),
                HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<String> genericError(Exception ex) {
//        System.out.println("not able to catch");
//        return new ResponseEntity<>(ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
