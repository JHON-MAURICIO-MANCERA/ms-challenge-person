package co.com.sofka.usecase.createpersoncommand;

import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.model.sofkiano.gateways.SofkianoRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class CreatePersonCommandUseCase {

    //private final SofkianoRepository personRepository;
    Logger logger = Logger.getLogger(CreatePersonCommandUseCase.class.getName());

    public Mono<Sofkiano> execute(Sofkiano person) {
        logger.info("enter to CreatePersonCommandUseCase");
        return Mono.just(person)
        //       .flatMap(ss -> personRepository.findById(ss.getId()))
                .map(personSaved -> createPerson(person))
      //          .switchIfEmpty(Mono.just(createPerson(person)))
                .doOnNext(ms -> logger.info("aqui entreo y guardó" + ms))
         //       .flatMap(personRepository::save)
                .doOnError(error -> logger.info(error.getMessage()));


    }

    private Sofkiano createPerson(Sofkiano person) {
        return Sofkiano.builder()
                .id(UUID.randomUUID().toString())
                .name(person.getName())
                .address(person.getAddress())
                .currentClient(person.getCurrentClient())
                .identification(person.getIdentification())
                .identificationType(person.getIdentificationType())
                .isActive(person.getIsActive())
                .currentProject(person.getCurrentProject())
                .build();
    }

    private Sofkiano updatePerson(Sofkiano person, Sofkiano personSaved) {
        return personSaved.toBuilder()
                .id(personSaved.getId())
                .name(person.getName())
                .address(person.getAddress())
                .currentClient(person.getCurrentClient())
                .identification(person.getIdentification())
                .identificationType(person.getIdentificationType())
                .isActive(person.getIsActive())
                .build();
    }
}


