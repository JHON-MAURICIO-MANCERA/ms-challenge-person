package co.com.sofka.model.client.gateways;


import co.com.sofka.model.client.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientRepository {
    Mono<String> saveData(Client client);
    Mono<Client> findByClientId(String id);
    Flux<Client> findByStatus(String status);
}
