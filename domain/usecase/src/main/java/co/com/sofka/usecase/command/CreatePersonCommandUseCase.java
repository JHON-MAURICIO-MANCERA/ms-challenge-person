package co.com.sofka.usecase.command;

import co.com.sofka.model.enums.PersonErrorEnums;
import co.com.sofka.model.exception.PersonException;
import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class CreatePersonCommandUseCase {

    private final SofkianoRepository sofkianoRepository;
    Logger logger = Logger.getLogger(CreatePersonCommandUseCase.class.getName());

    public Mono<Sofkiano> execute(Sofkiano sofkiano) {
        logger.info("enter to CreatePersonCommandUseCase");
        return Mono.just(sofkiano)
               .filter(info -> !info.getId().isEmpty())
                .flatMap(this::validateUser)
                .switchIfEmpty(Mono.just(createSofkiano(sofkiano)))
                .flatMap(sofkianoRepository::saveData)
                .doOnError(error -> logger.info("Error creando sofkinao "+ error.getMessage()));


    }
    private  Mono< Sofkiano> validateUser  (Sofkiano sofkianotofind){
        return Mono.just(sofkianotofind)
                 .flatMap(infoSofkiano -> sofkianoRepository.findbysofkianoId(infoSofkiano.getId()))
                 .map(sofkianoSaved -> updateSofkiano(sofkianotofind, sofkianoSaved))
                .switchIfEmpty(Mono.error(new PersonException(PersonErrorEnums.USER_DOES_NOT_EXIST)));
    }

    private Sofkiano createSofkiano(Sofkiano sofkiano) {
        return sofkiano.toBuilder()
                .id(UUID.randomUUID().toString())
                .build();
    }

    private Sofkiano updateSofkiano(Sofkiano sofkiano, Sofkiano sofkianoSaved) {
        return sofkianoSaved.toBuilder()
                .id(sofkianoSaved.getId())
                .name(sofkiano.getName())
                .address(sofkiano.getAddress())
                .identification(sofkiano.getIdentification())
                .isActive(sofkiano.getIsActive())
                .projectId(sofkiano.getProjectId())
                .countryId(sofkiano.getCountryId())
                .identificationTypeId(sofkiano.getIdentificationTypeId())
                .roll(sofkiano.getRoll())
                .clientId(sofkiano.getClientId())
                .build();
    }
}


