package co.com.sofka.jpa.personDB.client;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

public interface JPAClientRepository extends CrudRepository<ClientData, String>, QueryByExampleExecutor<ClientData> {
}
