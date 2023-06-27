package co.com.sofka.model.exception;


import co.com.sofka.model.enums.PersonErrorEnums;

public class PersonException extends RuntimeException{
    public PersonException(PersonErrorEnums error) {
        super(error.name());
    }
}
