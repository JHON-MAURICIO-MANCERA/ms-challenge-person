package co.com.sofka.jpa.personDB.experience;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPAExperienceRepository extends CrudRepository<ExperienceData, String>, QueryByExampleExecutor<ExperienceData> {
}
