package co.com.sofka.jpa.personDB.identificationtype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "identification_type")
public class IdentificationTypeData {
    @Id
    private  String id;
    private  String name;
    private  String abbreviation;
    private  String status;
}
