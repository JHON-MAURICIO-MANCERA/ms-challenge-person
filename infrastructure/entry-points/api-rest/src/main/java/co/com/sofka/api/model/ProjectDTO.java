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
public class ProjectDTO {
    private String id;
    @NotEmpty(message = "name can not is Empty nor Null")
    private String name;
    @NotEmpty(message = "clienId can not is Empty nor Null")
    private String clientId;
}


