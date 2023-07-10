package co.com.sofka.jpa.personDB.experience;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.experience.gateways.ExperienceRepository;
import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;


@Slf4j
@Repository
public class JPAExperienceRepositoryAdapter extends AdapterOperations<Sofkiano, ExperienceData, String, JPAExperienceRepository> implements ExperienceRepository {

    public JPAExperienceRepositoryAdapter(JPAExperienceRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Sofkiano.class));
    }


}
