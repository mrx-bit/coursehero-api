package kz.iitu.hackday.coursehero.utils.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class UnauthorisedException extends RuntimeException {
    private ErrorResponse errorResponse;

    public UnauthorisedException(String message, String errorCode) {
        super(message);
        errorResponse = new ErrorResponse(message, errorCode, HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value());
    }
}

