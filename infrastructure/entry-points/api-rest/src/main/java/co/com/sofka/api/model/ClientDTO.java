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
public class ClientDTO {
    private String id;
    @NotEmpty(message = "nit can not is Empty nor Null")
    private String nit;
    @NotEmpty(message = "clientName can not is Empty nor Null")
    private String clientName;
    private String status;


}

