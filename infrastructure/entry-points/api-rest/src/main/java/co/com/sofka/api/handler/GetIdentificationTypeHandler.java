package co.com.sofka.api.handler;

import co.com.sofka.api.model.IdentificationTypeDTO;
import co.com.sofka.api.utils.ObjectConversionUtils;
import co.com.sofka.model.identificationtype.IdentificationType;
import co.com.sofka.usecase.handler.GetIdentificationTypeHandlerUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/ms-challenge-person/api/persons/get-identification-type")
@Api(tags = "IdentificationType  API")
public record GetIdentificationTypeHandler(
        GetIdentificationTypeHandlerUseCase getIdentificationTypeHandlerUseCase) {
    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping(path = "/active", params = {"status"}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "get identification type")
    public Flux<IdentificationTypeDTO> getCountryActives(@RequestParam(value = "status", required = true) final String status) {
        return getIdentificationTypeHandlerUseCase.execute(status)
                .map(this::toDTO);
    }

    private IdentificationTypeDTO toDTO(IdentificationType identificationType) {
        return ObjectConversionUtils.convertir(identificationType, IdentificationTypeDTO.class);
    }

}


