package co.com.sofka.jpa.personDB.identificationtype;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.country.Country;
import co.com.sofka.model.country.gateways.CountryRepository;
import co.com.sofka.model.identificationtype.IdentificationType;
import co.com.sofka.model.identificationtype.gateways.IdentificationTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Slf4j
@Repository
public class JPAIdentificationTypeRepositoryAdapter extends AdapterOperations<IdentificationType, IdentificationTypeData, String, JPAIdentificationType> implements IdentificationTypeRepository {

    public JPAIdentificationTypeRepositoryAdapter(JPAIdentificationType repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, IdentificationType.class));
    }


    @Override
    public Flux<IdentificationType> findByStatus(String status) {
        return Flux.fromIterable(repository.findByStatus(status))
                .map(this::toEntity);
    }
}
