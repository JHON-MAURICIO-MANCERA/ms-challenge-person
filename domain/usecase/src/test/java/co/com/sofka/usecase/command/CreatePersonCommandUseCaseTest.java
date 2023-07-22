package co.com.sofka.usecase.command;

import co.com.sofka.model.enums.PersonErrorEnums;
import co.com.sofka.model.exception.PersonException;
import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import java.util.UUID;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePersonCommandUseCaseTest{
    @InjectMocks
    private CreatePersonCommandUseCase createPersonCommandUseCase;

    @Mock
    private SofkianoRepository sofkianoRepository;

    private Sofkiano inputSofkiano;


    @BeforeEach
    void setUp() {
          inputSofkiano = Sofkiano.builder()
                .id(UUID.randomUUID().toString())
                .name("John Doe")
                .address("123 Main St")
                .identification("123456789")
                .isActive(true)
                .projectId(UUID.randomUUID().toString())
                .countryId(UUID.randomUUID().toString())
                .identificationTypeId(UUID.randomUUID().toString())
                .roll("roll123")
                .clientId(UUID.randomUUID().toString())
                .build();

    }

    @Test
    void testExecute_WhenValidSofkiano_ShouldSaveData() {

        when(sofkianoRepository.findbysofkianoId(ArgumentMatchers.any())).thenReturn(Mono.just(inputSofkiano));
        when(sofkianoRepository.saveData(inputSofkiano)).thenReturn(Mono.just(inputSofkiano));
        Mono<Sofkiano> result = createPersonCommandUseCase.execute(inputSofkiano);
        StepVerifier.create(result)
                .expectNext(inputSofkiano)
                .verifyComplete();
    }
 @Test
    void testExecute_WhenNotValidSofkiano() {

        when(sofkianoRepository.findbysofkianoId(ArgumentMatchers.any())).thenReturn(Mono.error(new PersonException(PersonErrorEnums.USER_DOES_NOT_EXIST)));
        Mono<Sofkiano> result = createPersonCommandUseCase.execute(inputSofkiano);
        StepVerifier.create(result)
                .expectErrorMessage(PersonErrorEnums.USER_DOES_NOT_EXIST.toString())
                .verify();
    }


}