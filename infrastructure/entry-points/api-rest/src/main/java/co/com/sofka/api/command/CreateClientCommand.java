package co.com.sofka.api.command;

import co.com.sofka.api.model.ClientDTO;
import co.com.sofka.api.model.ErrorDTO;
import co.com.sofka.api.utils.ObjectConversionUtils;
import co.com.sofka.model.client.Client;
import co.com.sofka.usecase.clientcommand.ClientCommandUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/ms-challenge-person/api/persons/create-client")
@AllArgsConstructor
@Api(tags = "Client API")
public class CreateClientCommand {

    private final ClientCommandUseCase clientCommandUseCase;
    private final ObjectMapper mapper;
    private final ObjectConversionUtils objectConversionUtils;


    @PostMapping(path = "/save-client", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Create a new client")
    public Mono<String> createPerson(@Valid @RequestBody ClientDTO clientDTO) {
        return clientCommandUseCase.createClient(toDomainObject(clientDTO));
    }

    private Client toDomainObject(ClientDTO clientDTO) {
        return ObjectConversionUtils.convertir(clientDTO, Client.class);
    }

    private ClientDTO toDTO(Client client) {

        return ObjectConversionUtils.convertir(client, ClientDTO.class);
    }

}
