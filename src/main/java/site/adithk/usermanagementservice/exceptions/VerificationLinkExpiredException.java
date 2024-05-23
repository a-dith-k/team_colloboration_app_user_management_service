package site.adithk.usermanagementservice.exceptions;

public class VerificationLinkExpiredException extends Throwable {
    public VerificationLinkExpiredException(String message) {
        super(message);
    }
}
