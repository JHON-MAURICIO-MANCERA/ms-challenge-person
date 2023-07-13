package co.com.sofka.api.command;

import co.com.sofka.api.model.ErrorDTO;
import co.com.sofka.api.model.SofkianoDTO;
import co.com.sofka.api.utils.ObjectConversionUtils;
import co.com.sofka.model.sofkiano.Sofkiano;
import co.com.sofka.usecase.createpersoncommand.CreatePersonCommandUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
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
@RequestMapping(value = "/api/ms-challenge-person/api/persons/create-persons")
@AllArgsConstructor
@Api(tags = "Person API")
public class CreatePersonCommand {
    private final CreatePersonCommandUseCase createPersonCommandUseCase;

    @PostMapping(path = "/save-person", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ApiOperation(value = "Create a new person")
    public Mono<SofkianoDTO> createPerson(@Valid @RequestBody SofkianoDTO sofkianoDTO) {
        return createPersonCommandUseCase.execute(toSofkiano(sofkianoDTO))
                .map(this::toSofkianoDTO);
    }

    private Sofkiano toSofkiano(SofkianoDTO sofkianoDTO) {
        return ObjectConversionUtils.convertir(sofkianoDTO,Sofkiano.class);
    }

    private SofkianoDTO toSofkianoDTO(Sofkiano sofkiano) {
        return ObjectConversionUtils.convertir(sofkiano,SofkianoDTO.class);
    }
}
