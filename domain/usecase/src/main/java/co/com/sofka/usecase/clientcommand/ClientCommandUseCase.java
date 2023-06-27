package co.com.sofka.usecase.clientcommand;

import co.com.sofka.model.client.ClientInformation;
import co.com.sofka.model.client.gateways.ClientRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class ClientCommandUseCase {
    private final ClientRepository clientRepository;
    Logger logger = Logger.getLogger(ClientCommandUseCase.class.getName());


    public Mono<ClientInformation> createClient(ClientInformation client) {
        logger.info("enter to CreatePersonCommandUseCase");
        return Mono.just(client)
                //.flatMap(clientInformation -> clientRepository.findByClientId(clientInformation.getId()))
                .map(clientSaved -> newClient(client))
                //.switchIfEmpty(Mono.just(newClient(client)))
                .doOnNext(ms -> logger.info("aqui entreo y guardó" + ms))
                .flatMap(clientRepository::saveData)
                .map(rr -> ClientInformation.builder().build())
                .doOnError(error -> logger.info(error.getMessage()));


    }

    private ClientInformation newClient(ClientInformation client) {

        return ClientInformation.builder()
                .id(UUID.randomUUID().toString())
                .clientName(client.getClientName())
                .nit(client.getNit())
                .project(client.getProject())
                .skills(client.getSkills())
                .build();
    }

    private ClientInformation updatePerson(ClientInformation client, ClientInformation clientSaved) {
        return client.toBuilder()
                .id(clientSaved.getId())
                .clientName(client.getClientName())
                .nit(client.getNit())
                .project(client.getProject())
                .skills(client.getSkills())
                .build();
    }
}


