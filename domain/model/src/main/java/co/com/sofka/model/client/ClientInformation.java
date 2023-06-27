package co.com.sofka.model.client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ClientInformation {
    private String id;
    private String nit;
    private String clientName;
    private String project;
    private List<String> skills;
}
