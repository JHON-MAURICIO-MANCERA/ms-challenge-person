package co.com.sofka.model.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Error {
    private String message;
}

