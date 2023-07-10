package co.com.sofka.model.sofkiano;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Sofkiano {
    private String id;
    private String identificationTypeId;
    private String name;
    private String identification;
    private String address;
    private Boolean isActive;
    private String clientId;
    private String projectId;
    private String roll;
    private String countryId;
}
