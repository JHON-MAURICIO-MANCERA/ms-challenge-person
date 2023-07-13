package co.com.sofka.usecase.clientcommand;

import co.com.sofka.model.client.Client;
import co.com.sofka.model.client.gateways.ClientRepository;
import co.com.sofka.model.enums.PersonErrorEnums;
import co.com.sofka.model.exception.PersonException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class ClientCommandUseCase {
    private final ClientRepository clientRepository;
    Logger logger = Logger.getLogger(ClientCommandUseCase.class.getName());


    public Mono<String> createClient(Client client) {
        logger.info("enter to CreatePersonCommandUseCase");
        return Mono.just(client)
                .filter(info -> !info.getId().isEmpty())
                .flatMap(this::validateClient)
                .switchIfEmpty(Mono.just(newClient(client)))
                .flatMap(clientRepository::saveData)
                .doOnError(error -> logger.info("Error creando cliente "+ error.getMessage()));


    }
    private  Mono<Client> validateClient  (Client client) {
        return Mono.just(client)
                .flatMap(clientInformation -> clientRepository.findByClientId(clientInformation.getId()))
                .map(clientSaved -> updatePerson(client,clientSaved))
                .switchIfEmpty(Mono.error(new PersonException(PersonErrorEnums.CLIENT_DOES_NOT_EXIST)));
    }

    private Client newClient(Client client) {
        return client.toBuilder()
                .id(UUID.randomUUID().toString())
                .build();
    }

    private Client updatePerson(Client client, Client clientSaved) {
        return clientSaved.toBuilder()
                .id(clientSaved.getId())
                .clientName(client.getClientName())
                .nit(client.getNit())
                .build();
    }
}


