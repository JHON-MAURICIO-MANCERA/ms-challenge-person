package co.com.sofka.api.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class SofkianoDTO {
    private String id;
    @NotEmpty(message = "identificationType can not is Empty nor Null")
    private String identificationTypeId;
    @NotEmpty(message = "name can not is Empty nor Null")
    private String name;
    @NotEmpty(message = "identification can not is Empty nor Null")
    private String identification;
    private String address;
    private Boolean isActive;
    private String clientId;
    private String projectId;
    private String roll;
    private String countryId;

}
