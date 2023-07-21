package co.com.sofka.api.handler;

import co.com.sofka.api.model.ProjectDTO;
import co.com.sofka.api.utils.ObjectConversionUtils;
import co.com.sofka.model.project.Project;
import co.com.sofka.usecase.handler.GetProjectHandlerUseCase;
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
@RequestMapping(value = "/api/ms-challenge-person/api/persons/get-project")
@AllArgsConstructor
@Api(tags = "GetProject API")
public class GetProjectHandler {

    private final GetProjectHandlerUseCase getClientHandlerUseCase;

    @GetMapping(
            path = "/by-clientId",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(value = "get  project")
    public Flux<ProjectDTO> getclientActive(@RequestParam(value = "status", required = true)  String status,
                                            @RequestParam(value = "clientId", required = true)  String clientId) {
        return getClientHandlerUseCase.execute(status,clientId)
                .map(this::toDTO);
    }

    private ProjectDTO toDTO(Project project) {
        return ObjectConversionUtils.convertir(project, ProjectDTO.class);
    }

}
