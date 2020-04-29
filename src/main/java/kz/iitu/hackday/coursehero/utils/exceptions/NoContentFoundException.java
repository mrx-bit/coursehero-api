package kz.iitu.hackday.coursehero.utils.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class NoContentFoundException extends RuntimeException {
    private ErrorResponse errorResponse;

    public NoContentFoundException(String message, String errorCode) {
        super(message);
        errorResponse = new ErrorResponse(message, errorCode, HttpStatus.NO_CONTENT, HttpStatus.NO_CONTENT.value());
    }
}
