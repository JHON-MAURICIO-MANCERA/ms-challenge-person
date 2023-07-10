package co.com.sofka.jpa.personDB.client;


import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
@Entity
@Table(name = "client")
public class ClientData {
    @Id
    private String id;
    private String nit;
    @Column(name = "client_name")
    private String clientName;

}