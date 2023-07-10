package co.com.sofka.jpa.personDB.experience;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "sofkiano")
public class ExperienceData {
    @Id
    private String id;
    @Column(name = "identification_type")
    private String identificationType;
    private String name;
    private String identification;
    private String address;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "current_client")
    private String currentClient;
    @Column(name = "current_project")
    private String currentProject;
}
