package co.com.sofka.model.exception;


import co.com.sofka.model.enums.PersonErrorEnums;

public class AdminException extends RuntimeException{
    public AdminException(PersonErrorEnums error) {
        super(error.name());
    }
}
