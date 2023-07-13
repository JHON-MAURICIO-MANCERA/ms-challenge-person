package co.com.sofka.jpa.personDB.country;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.country.Country;
import co.com.sofka.model.country.gateways.CountryRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Slf4j
@Repository
public class JPACountryRepositoryAdapter extends AdapterOperations<Country, CountryData, String, JPACountryRepository> implements CountryRepository {

    public JPACountryRepositoryAdapter(JPACountryRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Country.class));
    }


    @Override
    public Flux<Country> findByStatus(String status) {
        return Flux.fromIterable(repository.findByStatus(status))
                .map(this::toEntity);
    }
}
