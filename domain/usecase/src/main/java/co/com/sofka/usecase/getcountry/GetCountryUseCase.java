package co.com.sofka.usecase.getcountry;

import co.com.sofka.model.country.Country;
import co.com.sofka.model.country.gateways.CountryRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.logging.Logger;

@RequiredArgsConstructor
public class GetCountryUseCase {
    Logger logger = Logger.getLogger(GetCountryUseCase.class.getName());
    private final CountryRepository countryRepository;

    public Flux<Country> execute(String status){
        logger.info("Start to get countries active");
        return countryRepository.findByStatus(status)
                .doOnError(error -> logger.info("Search country failed "+ error.getMessage()));

    }
}
