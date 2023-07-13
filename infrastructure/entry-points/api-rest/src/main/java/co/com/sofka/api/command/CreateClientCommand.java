package co.com.sofka.api.command;

import co.com.sofka.api.model.ClientDTO;
import co.com.sofka.api.utils.ObjectConversionUtils;
import co.com.sofka.model.client.Client;
import co.com.sofka.usecase.clientcommand.ClientCommandUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api/ms-challenge-person/api/persons/create-client")
@AllArgsConstructor
@Api(tags = "Client API")
public class CreateClientCommand {

    private final ClientCommandUseCase clientCommandUseCase;


    @PostMapping(path = "/save-client", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Create a new client")
    public Mono<String> createPerson(@Valid @RequestBody ClientDTO clientDTO) {
        return clientCommandUseCase.createClient(toDomainObject(clientDTO));
    }

    private Client toDomainObject(ClientDTO clientDTO) {
        return ObjectConversionUtils.convertir(clientDTO, Client.class);
    }

}
