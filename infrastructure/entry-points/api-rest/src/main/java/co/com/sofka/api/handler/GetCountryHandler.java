package co.com.sofka.api.handler;

import co.com.sofka.api.model.CountryDTO;
import co.com.sofka.api.utils.ObjectConversionUtils;
import co.com.sofka.model.country.Country;
import co.com.sofka.usecase.handler.GetCountryHandlerUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping(value = "/api/ms-challenge-person/api/persons/get-country")
@Api(tags = "Country API")
public record GetCountryHandler(GetCountryHandlerUseCase getCountryHandlerUseCase) {

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping(path = "/by-status", params = {"status"}, produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "get  country")
    public Flux<CountryDTO> getCountryActives(@RequestParam(value = "status", required = true) final String status) {
        return getCountryHandlerUseCase.execute(status)
                .map(this::toDTO);
    }

    private CountryDTO toDTO(Country country) {
        return ObjectConversionUtils.convertir(country, CountryDTO.class);
    }

}
