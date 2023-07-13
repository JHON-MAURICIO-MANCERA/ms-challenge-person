package co.com.sofka.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ErrorDTO {
    private  String objectName;
    private  String fieldName;
    private  String rejectedValue;
    private  String errorMessage;
}


