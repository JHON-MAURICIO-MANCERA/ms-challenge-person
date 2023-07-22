package co.com.sofka.usecase.handler;

import co.com.sofka.model.enums.PersonErrorEnums;
import co.com.sofka.model.enums.PersonStatusEnums;
import co.com.sofka.model.exception.PersonException;
import co.com.sofka.model.project.Project;
import co.com.sofka.model.project.gateways.ProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetProjectHandlerUseCaseTest {
    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private GetProjectHandlerUseCase getProjectHandlerUseCase;
    private Project project;

    @BeforeEach
    void setUp() {
        project = Project.builder()
                .id(UUID.randomUUID().toString())
                .name("TEST_NAME")
                .clientId("TEST_CLIENT_ID")
                .status("TEST_STATUS")
                .build();
    }

    @Test
    void testExecute_WhenProjectExist() {

        when(projectRepository.findByClientIdAndStatus(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Flux.just(project));
        Flux<Project> result = getProjectHandlerUseCase.execute(PersonStatusEnums.ACTIVE.getId(), UUID.randomUUID().toString());
        StepVerifier.create(result)
                .expectNext(project)
                .verifyComplete();
    }

    @Test
    void testExecute_WhenNoActiveProject() {

        when(projectRepository.findByClientIdAndStatus(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(Flux.error(new PersonException(PersonErrorEnums.CLIENT_WITHOUT_PROJECTS)));
        Flux<Project> result = getProjectHandlerUseCase.execute(UUID.randomUUID().toString(), UUID.randomUUID().toString());
        StepVerifier.create(result)
                .verifyError(PersonException.class);
    }
}