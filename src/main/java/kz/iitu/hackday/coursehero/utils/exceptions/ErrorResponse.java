package kz.iitu.hackday.coursehero.utils.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse implements Serializable {

    public ErrorResponse(String errorMsg, String errorCode, HttpStatus responseStatus, int responseCode) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
        this.responseStatus = responseStatus;
        this.responseCode = responseCode;
    }

    private String errorMsg;
    private String errorCode;
    private HttpStatus responseStatus;
    private int responseCode;
    private String details;
}



