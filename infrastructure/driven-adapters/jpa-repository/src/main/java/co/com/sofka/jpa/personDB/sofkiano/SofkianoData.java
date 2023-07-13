package co.com.sofka.jpa.personDB.sofkiano;

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
public class SofkianoData {

    @Id
    private String id;
    @Column(name = "identification_type_id")
    private String identificationTypeId;
    private String name;
    private String identification;
    private String address;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "client_id")
    private String clientId;
    @Column(name = "project_id")
    private String projectId;
    private String roll;
    @Column(name = "country_id")
    private String countryId;
}
