package co.com.sofka.jpa.personDB.project;

import co.com.sofka.jpa.helper.AdapterOperations;
import co.com.sofka.model.project.Project;
import co.com.sofka.model.project.gateways.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Slf4j
@Repository
public class JPAProjectRepositoryAdapter extends AdapterOperations<Project, ProjectData, String, JPAProjectRepository> implements ProjectRepository
{

    public JPAProjectRepositoryAdapter(JPAProjectRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Project.class));
    }

    @Override
    public Flux<Project> findByClientIdAndStatus(String clientId, String status) {
        return Flux.fromIterable(repository.findByClientIdAndStatus(clientId,status))
                .map(this::toEntity);
    }
}
