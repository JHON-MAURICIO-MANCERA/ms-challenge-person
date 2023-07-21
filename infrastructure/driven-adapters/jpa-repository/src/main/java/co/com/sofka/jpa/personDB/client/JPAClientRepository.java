package co.com.sofka.jpa.personDB.client;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface JPAClientRepository extends CrudRepository<ClientData, String>, QueryByExampleExecutor<ClientData> {
    List<ClientData> findByStatus(String status);
}
