package co.com.sofka.model.client.gateways;


import co.com.sofka.model.client.ClientInformation;
import reactor.core.publisher.Mono;

public interface ClientRepository {
    Mono<Void> saveData(ClientInformation client);
    Mono<ClientInformation> findByClientId(String id);
}
