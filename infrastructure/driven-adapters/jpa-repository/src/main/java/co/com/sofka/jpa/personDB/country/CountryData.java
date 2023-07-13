package co.com.sofka.jpa.personDB.country;

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
@Table(name = "country")
public class CountryData {
    @Id
    private String id;
    private String name;
    private String status;
}
