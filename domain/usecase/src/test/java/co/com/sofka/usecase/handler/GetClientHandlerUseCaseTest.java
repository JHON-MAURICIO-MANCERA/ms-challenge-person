package co.com.sofka.usecase.handler;

import co.com.sofka.model.client.Client;
import co.com.sofka.model.client.gateways.ClientRepository;
import co.com.sofka.model.enums.PersonErrorEnums;
import co.com.sofka.model.enums.PersonStatusEnums;
import co.com.sofka.model.exception.PersonException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.UUID;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetClientHandlerUseCaseTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private  GetClientHandlerUseCase getClientHandlerUseCase;

    private Client client;

    @BeforeEach
    void setUp() {
        client = Client.builder()
                .id(UUID.randomUUID().toString())
                .nit("899987")
                .clientName("123 Main St")
                .status("123456789")
                .build();

    }

    @Test
    void testExecute_WhenClientExist() {

        when(clientRepository.findByStatus(ArgumentMatchers.any())).thenReturn(Flux.just(client));
        Flux<Client> result = getClientHandlerUseCase.execute(PersonStatusEnums.ACTIVE.getId());
        StepVerifier.create(result)
                .expectNext(client)
                .verifyComplete();
    }

    @Test
    void testExecute_WhenNoActiveClients(){

        when(clientRepository.findByStatus(ArgumentMatchers.any())).thenReturn(Flux.error(new PersonException(PersonErrorEnums.NO_ACTIVE_CLIENTS)));
        Flux<Client> result = getClientHandlerUseCase.execute(UUID.randomUUID().toString());
        StepVerifier.create(result)
                .verifyError(PersonException.class);

    }

}