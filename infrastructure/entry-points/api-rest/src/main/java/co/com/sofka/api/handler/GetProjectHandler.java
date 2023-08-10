package co.com.sofka.api.handler;

import co.com.sofka.api.model.ProjectDTO;
import co.com.sofka.api.utils.ObjectConversionUtils;
import co.com.sofka.model.project.Project;
import co.com.sofka.usecase.handler.GetProjectHandlerUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/api/ms-challenge-person/api/persons/get-project")
@Api(tags = "GetProject API")
public record GetProjectHandler(GetProjectHandlerUseCase getClientHandlerUseCase) {

    @CrossOrigin(origins = "http://localhost:3000/")
    @GetMapping(
            path = "/by-clientId",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(value = "get  project")
    public Flux<ProjectDTO> getclientActive(@RequestParam(value = "status", required = true) String status,
                                            @RequestParam(value = "clientId", required = true) String clientId) {
        return getClientHandlerUseCase.execute(status, clientId)
                .map(this::toDTO);
    }

    private ProjectDTO toDTO(Project project) {
        return ObjectConversionUtils.convertir(project, ProjectDTO.class);
    }

}
