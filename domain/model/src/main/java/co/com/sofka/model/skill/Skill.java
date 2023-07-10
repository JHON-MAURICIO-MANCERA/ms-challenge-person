package co.com.sofka.model.skill;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class Skill {
    private  String id;
    private String clientId;
    private String skill;
}
