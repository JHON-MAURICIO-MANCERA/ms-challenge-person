package co.com.sofka.api.command;

import co.com.sofka.api.model.ClientDTO;
import co.com.sofka.api.model.ErrorDTO;
import co.com.sofka.api.model.SofkianoDTO;
import co.com.sofka.api.utils.ObjectConversionUtils;
import co.com.sofka.model.client.ClientInformation;
import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.usecase.clientcommand.ClientCommandUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    public Mono<ClientDTO> createPerson(@Valid @RequestBody ClientDTO clientDTO) {
        return clientCommandUseCase.createClient(toDomainObject(clientDTO))
                .map(this::toDTO);
    }


    private ClientInformation toDomainObject(ClientDTO clientDTO) {
        return ObjectConversionUtils.convertir(clientDTO, ClientInformation.class);
    }

    private ClientDTO toDTO(ClientInformation client) {
        return ObjectConversionUtils.convertir(client, ClientDTO.class);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(CreateClientCommand::getErrorDto)
                .collect(Collectors.toList());
    }

    private static ErrorDTO getErrorDto(ObjectError error) {
        String field = null;
        String rejectedValue = null;

        if (error instanceof FieldError) {
            FieldError fieldError = (FieldError) error;
            field = fieldError.getField();
            rejectedValue = String.valueOf(fieldError.getRejectedValue());
        }


        return new ErrorDTO(
                error.getObjectName(),
                field,
                rejectedValue,
                error.getDefaultMessage());
    }
}
