package co.com.sofka.usecase.handler;

import co.com.sofka.model.enums.PersonErrorEnums;
import co.com.sofka.model.enums.PersonStatusEnums;
import co.com.sofka.model.exception.PersonException;
import co.com.sofka.model.identificationtype.IdentificationType;
import co.com.sofka.model.identificationtype.gateways.IdentificationTypeRepository;
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
class GetIdentificationTypeHandlerUseCaseTest {
    @Mock
    private IdentificationTypeRepository identificationTypeRepository;

    @InjectMocks
    private GetIdentificationTypeHandlerUseCase getIdentificationTypeHandlerUseCase;
    private IdentificationType identificationType;

    @BeforeEach
    void setUp() {
        identificationType= IdentificationType.builder()
                .id(UUID.randomUUID().toString())
                .name("TEST_NAME")
                .abbreviation("TEST_ABBREVIATION")
                .status("TEST_STATUS")
                .build();
    }

    @Test
    void testExecute_WhenIdentificationTypeExist() {

        when(identificationTypeRepository.findByStatus(PersonStatusEnums.ACTIVE.getId())).thenReturn(Flux.just(identificationType));
        Flux<IdentificationType> result = getIdentificationTypeHandlerUseCase.execute(PersonStatusEnums.ACTIVE.getId());
        StepVerifier.create(result)
                .expectNext(identificationType)
                .verifyComplete();
    }
    @Test
    void testExecute_WhenNoActiveIdentificationType() {

        when(identificationTypeRepository.findByStatus(ArgumentMatchers.any())).thenReturn(Flux.error(new PersonException(PersonErrorEnums.NO_ACTIVE_IDENTIFICATION_TYPE)));
        Flux<IdentificationType> result = getIdentificationTypeHandlerUseCase.execute(UUID.randomUUID().toString());
        StepVerifier.create(result)
                .verifyError(PersonException.class);
    }

}