package co.com.sofka.jpa.personDB.identificationtype;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface JPAIdentificationType extends CrudRepository<IdentificationTypeData, String>, QueryByExampleExecutor<IdentificationTypeData> {
List<IdentificationTypeData> findByStatus(String status);
}
