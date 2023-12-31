package co.com.sofka.model.identificationtype;
import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class IdentificationType {
    private  String id;
    private  String name;
    private  String abbreviation;
    private  String status;
}
