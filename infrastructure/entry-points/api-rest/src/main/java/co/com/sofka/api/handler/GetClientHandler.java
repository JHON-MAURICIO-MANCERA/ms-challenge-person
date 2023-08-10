package co.com.sofka.api.handler;

import co.com.sofka.api.model.ClientDTO;
import co.com.sofka.api.utils.ObjectConversionUtils;
import co.com.sofka.model.client.Client;
import co.com.sofka.usecase.handler.GetClientHandlerUseCase;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/ms-challenge-person/api/persons/get-client")
@Api(tags = "GetClient API")
public record GetClientHandler(GetClientHandlerUseCase getClientHandlerUseCase) {

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping(
            path = "/by-status", params = {"status"},
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(value = "get  client")
    public Flux<ClientDTO> getclientActive(@RequestParam(value = "status", required = true) final String status) {
        return getClientHandlerUseCase.execute(status)
                .map(this::toDTO);
    }

    private ClientDTO toDTO(Client client) {
        return ObjectConversionUtils.convertir(client, ClientDTO.class);
    }


}
