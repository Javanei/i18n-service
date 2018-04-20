package br.com.javanei.i18n.v1.project;

import br.com.javanei.i18n.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
@Api(description = "Project administration", tags = "Projects")
public class ProjectRest {
    private ProjectService projectService;

    public ProjectRest(ProjectService projectService) {
        this.projectService = projectService;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Add a new project")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = ProjectResponseDTO.class),
            @ApiResponse(code = 400, message = "There was an error creating a project"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 500, message = "Internal server error, see response for more details")
    })
    public ResponseEntity<ProjectResponseDTO> createProject(@RequestBody ProjectCreateDTO project) {
        ProjectResponseDTO result = ProjectMapper.entityToResponseDTO(
                projectService.create(ProjectMapper.entityFromCreateDTO(project)));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Find a existing project")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = ProjectResponseDTO.class),
            @ApiResponse(code = 400, message = "There was an error finding a project"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 404, message = "Project not found"),
            @ApiResponse(code = 500, message = "Internal server error, see response for more details")
    })
    public ResponseEntity<ProjectResponseDTO> findProject(
            @PathVariable("id") String id) {
        ProjectResponseDTO result = ProjectMapper.entityToResponseDTO(
                projectService.findById(id));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update a existing Project")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = ProjectResponseDTO.class),
            @ApiResponse(code = 400, message = "There was an error updating a Project"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 404, message = "Project not found"),
            @ApiResponse(code = 500, message = "Internal server error, see response for more details")
    })
    public ResponseEntity<ProjectResponseDTO> updateProject(
            @PathVariable("id") String id,
            @RequestBody ProjectUpdateDTO project) {
        ProjectResponseDTO result = ProjectMapper.entityToResponseDTO(
                projectService.update(ProjectMapper.entityFromUpdateDTO(id, project)));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Delete a existing project")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = ProjectResponseDTO.class),
            @ApiResponse(code = 400, message = "There was an error deleting a project"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 404, message = "Project not found"),
            @ApiResponse(code = 500, message = "Internal server error, see response for more details")
    })
    public ResponseEntity<ProjectResponseDTO> deleteProject(
            @PathVariable("id") String id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("List projects")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = List.class),
            @ApiResponse(code = 400, message = "There was an error listing projects"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 500, message = "Internal server error, see response for more details")
    })
    public ResponseEntity<List<ProjectResponseDTO>> findProjects() {
        List<ProjectResponseDTO> result = ProjectMapper.entitiesToResultDTO(
                projectService.findAll());
        return ResponseEntity.ok(result);
    }
}
