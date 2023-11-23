package bo.jads.myfinancesbackend.app.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.Date;

@Getter
public class ErrorResponse {

    private final Date timestamp;
    private final Integer status;
    private final String error;
    private final Class<? extends Exception> exception;
    private final String message;
    private final String path;

    public ErrorResponse(HttpStatusCode statusCode, Class<? extends Exception> exception, String message, String path) {
        this.timestamp = new Date();
        this.status = statusCode.value();
        this.error = HttpStatus.valueOf(statusCode.value()).getReasonPhrase();
        this.exception = exception;
        this.message = message;
        this.path = path;
    }

    public ErrorResponse(HttpStatusCode statusCode, String message, String path) {
        this(statusCode, null, message, path);
    }

}
