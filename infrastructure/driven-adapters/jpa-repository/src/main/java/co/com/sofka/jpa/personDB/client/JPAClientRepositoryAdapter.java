package co.com.sofka.jpa.personDB.client;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.client.Client;
import co.com.sofka.model.client.gateways.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Repository
public class JPAClientRepositoryAdapter extends AdapterOperations<Client, ClientData, String, JPAClientRepository> implements ClientRepository
{

    public JPAClientRepositoryAdapter(JPAClientRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Client.class));
    }
    @Override
    public Mono<String> saveData(Client clientInformation) {
        return Mono.justOrEmpty(clientInformation)
                .map(this::toData)
                .flatMap(budget1 -> Mono.justOrEmpty(repository.save(budget1)))
                .flatMap(ss -> Mono.justOrEmpty(ss.getId()));
    }

    @Override
    public Mono<Client> findByClientId(String id) {
        return Mono.justOrEmpty(repository.findById(id))
                .map(this::toEntity);
    }

    @Override
    public Flux<Client> findByStatus(String status) {
        return Flux.fromIterable(repository.findByStatus(status))
                .map(this::toEntity);
    }
}
