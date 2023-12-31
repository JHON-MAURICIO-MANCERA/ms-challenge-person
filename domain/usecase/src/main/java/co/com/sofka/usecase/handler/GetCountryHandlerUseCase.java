package co.com.sofka.usecase.handler;

import co.com.sofka.model.country.Country;
import co.com.sofka.model.country.gateways.CountryRepository;
import co.com.sofka.model.enums.PersonErrorEnums;
import co.com.sofka.model.exception.PersonException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class GetCountryHandlerUseCase {
    Logger logger = Logger.getLogger(GetCountryHandlerUseCase.class.getName());
    private final CountryRepository countryRepository;

    public Flux<Country> execute(String status){
        logger.info("Start to get countries active");
        return countryRepository.findByStatus(status)
                .sort(Comparator.comparing(Country::getName))
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PersonException(PersonErrorEnums.NO_ACTIVE_COUNTRIES))))
                .doOnError(error -> logger.info("Search country failed "+ error.getMessage()));

    }
}
