package co.com.sofka.model.project.gateways;

import co.com.sofka.model.project.Project;
import reactor.core.publisher.Flux;

public interface ProjectRepository {
    Flux<Project> findByClientIdAndStatus(String clientId,String status);
}
