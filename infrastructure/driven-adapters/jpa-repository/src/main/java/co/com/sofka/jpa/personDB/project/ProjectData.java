package co.com.sofka.jpa.personDB.project;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
@Entity
@Table(name = "project")
public class ProjectData {
    @Id
    private String id;
    private String name;
    @Column(name = "client_id")
    private String clientId;
    private String status;

}