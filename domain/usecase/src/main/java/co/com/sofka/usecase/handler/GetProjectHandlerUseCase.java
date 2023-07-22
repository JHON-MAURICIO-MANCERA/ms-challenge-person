package co.com.sofka.usecase.handler;

import co.com.sofka.model.enums.PersonErrorEnums;
import co.com.sofka.model.exception.PersonException;

import co.com.sofka.model.project.Project;
import co.com.sofka.model.project.gateways.ProjectRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.logging.Logger;

@RequiredArgsConstructor
public class GetProjectHandlerUseCase {
    Logger logger = Logger.getLogger(GetProjectHandlerUseCase.class.getName());
    private final ProjectRepository projectRepository;

    public Flux<Project> execute(String status, String clientId) {
        logger.info("start  to getProjectUseCase");
        return projectRepository.findByClientIdAndStatus(clientId, status)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PersonException(PersonErrorEnums.CLIENT_WITHOUT_PROJECTS))))
                .sort(Comparator.comparing(Project::getName))
                .doOnError(error -> logger.info("Search project  failed " + error.getMessage()));
    }
}
