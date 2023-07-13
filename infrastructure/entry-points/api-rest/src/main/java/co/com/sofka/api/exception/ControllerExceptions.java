package co.com.sofka.api.exception;


import co.com.sofka.api.model.ErrorDTO;
import co.com.sofka.model.exception.Error;
import co.com.sofka.model.exception.PersonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestControllerAdvice
public class ControllerExceptions {
    Logger logger = Logger.getLogger(ControllerExceptions.class.getName());


    @ExceptionHandler(PersonException.class)
    public ResponseEntity<Error> adminExceptionHandler(PersonException exception){
        Error error = Error.builder().message(exception.getMessage()).build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public List<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException ex){

        List<ErrorDTO> errorList = new ArrayList<>();

        ex.getBindingResult()
                .getAllErrors()
                .forEach(error -> {
                    ErrorDTO errorDTO = ControllerExceptions.getErrorDto(error);
                    logger.info(errorDTO.toString());
                    errorList.add(errorDTO);
                });

        return errorList;
    }

    private static ErrorDTO getErrorDto(ObjectError error) {
        String field = null;
        String rejectedValue = null;

        if (error instanceof FieldError fieldError) {
            field = fieldError.getField();
            rejectedValue = String.valueOf(fieldError.getRejectedValue());
        }


        return new ErrorDTO(
                error.getObjectName(),
                field,
                rejectedValue,
                error.getDefaultMessage());
    }
}
