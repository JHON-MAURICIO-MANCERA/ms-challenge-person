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
    private String identificationType;
    private String name;
    private String identification;
    private String address;
    private Boolean isActive;
    private String currentClient;
    private String currentProject;
}
