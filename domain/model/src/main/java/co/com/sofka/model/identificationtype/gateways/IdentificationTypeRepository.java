package co.com.sofka.model.identificationtype.gateways;

import co.com.sofka.model.identificationtype.IdentificationType;
import reactor.core.publisher.Flux;

public interface IdentificationTypeRepository {
    Flux<IdentificationType> findByStatus(String status);
}
