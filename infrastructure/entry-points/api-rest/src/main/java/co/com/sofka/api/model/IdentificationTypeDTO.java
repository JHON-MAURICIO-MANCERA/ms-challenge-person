package co.com.sofka.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class IdentificationTypeDTO {
    private  String id;
    private  String name;
    private  String abbreviation;
    private  String status;
}
