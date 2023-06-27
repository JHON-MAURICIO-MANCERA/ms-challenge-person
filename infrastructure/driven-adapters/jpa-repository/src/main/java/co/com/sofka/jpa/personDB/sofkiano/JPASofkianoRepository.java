package co.com.sofka.jpa.personDB.sofkiano;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPASofkianoRepository extends CrudRepository<SofkianoData, String>, QueryByExampleExecutor<SofkianoData> {
}
