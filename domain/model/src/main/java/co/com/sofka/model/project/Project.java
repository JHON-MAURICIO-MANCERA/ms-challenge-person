package co.com.sofka.model.project;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder(toBuilder = true)
public class Project {
    private String id;
    private String name;
    private String clientId;
    private String status;


}
