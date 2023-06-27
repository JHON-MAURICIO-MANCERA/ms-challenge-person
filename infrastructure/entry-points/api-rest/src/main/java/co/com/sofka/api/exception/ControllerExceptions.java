package co.com.sofka.api.exception;


import co.com.sofka.model.exception.AdminException;
import co.com.sofka.model.exception.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptions {

    @ExceptionHandler(value = AdminException.class)
    public ResponseEntity<Error> adminExceptionHandler(AdminException exception){
        Error error = Error.builder().message(exception.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
