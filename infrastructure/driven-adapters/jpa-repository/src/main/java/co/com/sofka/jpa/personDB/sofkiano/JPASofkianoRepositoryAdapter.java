package co.com.sofka.jpa.personDB.sofkiano;

import co.com.sofka.jpa.helper.AdapterOperations;

import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Slf4j
@Repository
public class JPASofkianoRepositoryAdapter extends AdapterOperations<Sofkiano, SofkianoData, String, JPASofkianoRepository> implements SofkianoRepository {

    public JPASofkianoRepositoryAdapter(JPASofkianoRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Sofkiano.class));
    }

    @Override
    public Mono<Void> saveData(Sofkiano sofkiano) {
        return Mono.justOrEmpty(sofkiano)
                .map(this::toData)
                .flatMap(sofkianoData -> Mono.justOrEmpty(repository.save(sofkianoData)))
                .then();
    }

    @Override
    public Mono<Sofkiano> findbysofkianoId(String id) {
        return Mono.justOrEmpty(repository.findById(id)).map(this::toEntity);
    }
}
