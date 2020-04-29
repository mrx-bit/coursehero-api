package kz.iitu.hackday.coursehero.utils.exceptionHandler;

import kz.iitu.hackday.coursehero.utils.constants.ErrorMessageConstants.*;
import kz.iitu.hackday.coursehero.utils.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = InvalidArgumentDataException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(InvalidArgumentDataException e) {
        log.error(e.getErrorResponse().toString(), e);
        return new ResponseEntity<>(e.getErrorResponse(), e.getErrorResponse().getResponseStatus());
    }

    // error handle for @Valid
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e,
                                                                               WebRequest request) {
        log.error(e.getMessage(), e);

        //Get all errors
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse errorResponse
                = new ErrorResponse(MethodArgumentNotValid.MESSAGE + errors,
                MethodArgumentNotValid.ERROR_CODE,
                HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(),
                request.getDescription(false));

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NoContentFoundException.class)
    public ResponseEntity<ErrorResponse> handleNoContentFoundException(NoContentFoundException e) {
        log.error(e.getErrorResponse().toString(), e);
        return new ResponseEntity<>(e.getErrorResponse(), e.getErrorResponse().getResponseStatus());
    }

    @ExceptionHandler(value = UnauthorisedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorisedException(UnauthorisedException e) {
        log.error(e.getErrorResponse().toString(), e);
        return new ResponseEntity<>(e.getErrorResponse(), e.getErrorResponse().getResponseStatus());
    }

    // handle all other exceptions.
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorResponse> handleAllExceptions(Exception e, WebRequest request) {
        log.error(e.getMessage() + " : " + request.getDescription(true), e);
        ErrorResponse errorResponse
                = new ErrorResponse(InternalServerError.MESSAGE,
                InternalServerError.ERROR_CODE,
                HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(),
                request.getDescription(false));

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
