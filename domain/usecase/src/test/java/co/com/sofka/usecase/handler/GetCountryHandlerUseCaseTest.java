package co.com.sofka.usecase.handler;

import co.com.sofka.model.country.Country;
import co.com.sofka.model.country.gateways.CountryRepository;
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
class GetCountryHandlerUseCaseTest {
    @Mock
    private CountryRepository countryRepository;

    @InjectMocks
    private GetCountryHandlerUseCase getCountryHandlerUseCase;
    private Country country;

    @BeforeEach
    void setUp() {
        country= Country.builder()
                .id(UUID.randomUUID().toString())
                .name("TEST_NAME")
                .status("TEST_STATUS")
                .build();
    }

    @Test
    void testExecute_WhenCountryExist() {

        when(countryRepository.findByStatus(ArgumentMatchers.any())).thenReturn(Flux.just(country));
        Flux<Country> result = getCountryHandlerUseCase.execute(PersonStatusEnums.ACTIVE.getId());
        StepVerifier.create(result)
                .expectNext(country)
                .verifyComplete();
    }
    @Test
    void testExecute_WhenNoActiveCountry() {

        when(countryRepository.findByStatus(ArgumentMatchers.any())).thenReturn(Flux.error(new PersonException(PersonErrorEnums.NO_ACTIVE_COUNTRIES)));
        Flux<Country> result = getCountryHandlerUseCase.execute(UUID.randomUUID().toString());
        StepVerifier.create(result)
                .verifyError(PersonException.class);
    }
}