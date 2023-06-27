package co.com.sofka.jpa.personDB.client;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.client.ClientInformation;
import co.com.sofka.model.client.gateways.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class JPAClientRepositoryAdapter extends AdapterOperations<ClientInformation, ClientData, String, JPAClientRepository> implements ClientRepository
{

    public JPAClientRepositoryAdapter(JPAClientRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, ClientInformation.class));
    }
    @Override
    public Mono<Void> saveData(ClientInformation clientInformation) {
        return Mono.justOrEmpty(clientInformation)
                .map(this::toData)
                .flatMap(budget1 -> Mono.justOrEmpty(repository.save(budget1)))
                .then();
    }

    @Override
    public Mono<ClientInformation> findByClientId(String id) {
        return Mono.justOrEmpty(repository.findById(id)).map(this::toEntity);
    }
}
