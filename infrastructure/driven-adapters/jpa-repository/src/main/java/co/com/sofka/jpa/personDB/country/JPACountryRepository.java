package co.com.sofka.jpa.personDB.country;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface JPACountryRepository extends CrudRepository<CountryData, String>, QueryByExampleExecutor<CountryData> {
List<CountryData> findByStatus(String status);
}
