package co.com.sofka.api.handler;

import co.com.sofka.api.model.IdentificationTypeDTO;
import co.com.sofka.api.utils.ObjectConversionUtils;
import co.com.sofka.model.identificationtype.IdentificationType;
import co.com.sofka.usecase.handler.GetIdentificationTypeUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/ms-challenge-person/api/persons/get-identification-type")
@AllArgsConstructor
@Api(tags = "IdentificationType  API")
public class GetIdentificationTypeHandler {

    private final GetIdentificationTypeUseCase getIdentificationTypeUseCase;

    @GetMapping(path = "/active", params = {"status"}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "get identification type")
    public Flux<IdentificationTypeDTO> getCountryActives(@RequestParam(value = "status", required = true) final String status) {
        return getIdentificationTypeUseCase.execute(status)
                .map(this::toDTO);
    }

    private IdentificationTypeDTO toDTO(IdentificationType identificationType) {
        return ObjectConversionUtils.convertir(identificationType, IdentificationTypeDTO.class);
    }

}


