package co.com.sofka.model.experience;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ExperienceInformation {
    private String id;
    private String sofkianoId;
    private String clientid;
    private String startDate;
    private String finishDate;

}
