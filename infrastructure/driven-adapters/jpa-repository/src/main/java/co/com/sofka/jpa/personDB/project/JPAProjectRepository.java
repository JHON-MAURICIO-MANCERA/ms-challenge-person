package co.com.sofka.jpa.personDB.project;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface JPAProjectRepository extends CrudRepository<ProjectData, String>, QueryByExampleExecutor<ProjectData> {
    List<ProjectData> findByClientIdAndStatus(String clientId, String status);
}
