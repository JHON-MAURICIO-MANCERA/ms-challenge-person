package co.com.sofka.usecase.handler;

import co.com.sofka.model.client.Client;
import co.com.sofka.model.client.gateways.ClientRepository;
import co.com.sofka.model.enums.PersonErrorEnums;
import co.com.sofka.model.exception.PersonException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class GetClientHandlerUseCase {
    Logger logger = Logger.getLogger(GetClientHandlerUseCase.class.getName());
    private final ClientRepository clientRepository;


    public Flux<Client> execute(String status){
        logger.info("Start to get client active");
        return clientRepository.findByStatus(status)
                .sort(Comparator.comparing(Client::getClientName))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PersonException(PersonErrorEnums.NO_ACTIVE_CLIENTS))))
                .doOnError(error -> logger.info("Search client failed "+ error.getMessage()));

    }
}
