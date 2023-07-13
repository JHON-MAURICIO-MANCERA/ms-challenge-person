package co.com.sofka.model.skill.gateways;


import co.com.sofka.model.skill.SkillClient;
import reactor.core.publisher.Mono;

public interface SkillClientRepository {
    Mono<Void> saveData(SkillClient client);
    Mono<SkillClient> findByClientId(String id);
}
