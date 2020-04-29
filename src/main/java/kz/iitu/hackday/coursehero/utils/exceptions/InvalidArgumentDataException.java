package kz.iitu.hackday.coursehero.utils.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class InvalidArgumentDataException extends RuntimeException {
    private ErrorResponse errorResponse;

    public InvalidArgumentDataException(String message, String errorCode) {
        super(message);
        errorResponse = new ErrorResponse(message, errorCode, HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value());
    }
}
