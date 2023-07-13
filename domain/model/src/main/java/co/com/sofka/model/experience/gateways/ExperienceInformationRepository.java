package co.com.sofka.model.experience.gateways;


import co.com.sofka.model.experience.ExperienceInformation;
import reactor.core.publisher.Mono;

public interface ExperienceInformationRepository {
    Mono<Void> saveData(ExperienceInformation client);
    Mono<ExperienceInformation> findByClientId(String id);
}
