package co.com.sofka.usecase.getidentificationtype;

import co.com.sofka.model.enums.PersonErrorEnums;
import co.com.sofka.model.exception.PersonException;
import co.com.sofka.model.identificationtype.IdentificationType;
import co.com.sofka.model.identificationtype.gateways.IdentificationTypeRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Comparator;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class GetIdentificationTypeUseCase {
    Logger logger = Logger.getLogger(GetIdentificationTypeUseCase.class.getName());
    private  final IdentificationTypeRepository identityTypeRepository;

    public Flux<IdentificationType> execute (String status){
        logger.info("start  to GetIdentificationTypeUseCase");
        return identityTypeRepository.findByStatus(status)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PersonException(PersonErrorEnums.NO_ACTIVE_IDENTIFICATION_TYPE))))
                .sort(Comparator.comparing(IdentificationType::getName))
                .doOnError(error -> logger.info("Search identification type failed "+ error.getMessage()));



    }
}
