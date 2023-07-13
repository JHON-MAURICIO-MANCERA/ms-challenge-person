package co.com.sofka.usecase.getidentificationtype;

import co.com.sofka.model.identificationtype.IdentificationType;
import co.com.sofka.model.identificationtype.gateways.IdentificationTypeRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import java.util.logging.Logger;

@RequiredArgsConstructor
public class GetIdentificationTypeUseCase {
    Logger logger = Logger.getLogger(GetIdentificationTypeUseCase.class.getName());
    private  final IdentificationTypeRepository identityTypeRepository;

    public Flux<IdentificationType> execute (String status){
        logger.info("start  to GetIdentificationTypeUseCase");
        return identityTypeRepository.findByStatus(status)
                .doOnError(error -> logger.info("Search identification type failed "+ error.getMessage()));



    }
}
