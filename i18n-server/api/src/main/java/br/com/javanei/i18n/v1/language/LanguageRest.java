package br.com.javanei.i18n.v1.language;

import br.com.javanei.i18n.service.LanguageService;
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
@RequestMapping("/api/v1/languages")
@Api(description = "Language administration", tags = {"Languages"})
public class LanguageRest {
    private LanguageService languageService;

    public LanguageRest(LanguageService languageService) {
        this.languageService = languageService;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Add a new language")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = LanguageResponseDTO.class),
            @ApiResponse(code = 400, message = "There was an error creating a language"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 500, message = "Internal server error, see response for more details")
    })
    public ResponseEntity<LanguageResponseDTO> createLanguage(@RequestBody LanguageCreateDTO language) {
        LanguageResponseDTO result = LanguageMapper.entityToResponseDTO(
                languageService.create(LanguageMapper.entityFromCreateDTO(language)));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Find a existing language")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = LanguageResponseDTO.class),
            @ApiResponse(code = 400, message = "There was an error finding a language"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 404, message = "Language not found"),
            @ApiResponse(code = 500, message = "Internal server error, see response for more details")
    })
    public ResponseEntity<LanguageResponseDTO> findLanguage(
            @PathVariable("id") String id) {
        LanguageResponseDTO result = LanguageMapper.entityToResponseDTO(
                languageService.findById(id));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update a existing language")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = LanguageResponseDTO.class),
            @ApiResponse(code = 400, message = "There was an error updating a language"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 404, message = "Language not found"),
            @ApiResponse(code = 500, message = "Internal server error, see response for more details")
    })
    public ResponseEntity<LanguageResponseDTO> updateLanguage(
            @PathVariable("id") String id,
            @RequestBody LanguageUpdateDTO language) {
        LanguageResponseDTO result = LanguageMapper.entityToResponseDTO(
                languageService.update(LanguageMapper.entityFromUpdateDTO(id, language)));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Delete a existing language")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = LanguageResponseDTO.class),
            @ApiResponse(code = 400, message = "There was an error deleting a language"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 404, message = "Language not found"),
            @ApiResponse(code = 500, message = "Internal server error, see response for more details")
    })
    public ResponseEntity<LanguageResponseDTO> deleteLanguage(
            @PathVariable("id") String id) {
        languageService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("List languages")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = List.class),
            @ApiResponse(code = 400, message = "There was an error listing languages"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 500, message = "Internal server error, see response for more details")
    })
    public ResponseEntity<List<LanguageResponseDTO>> findLanguages() {
        List<LanguageResponseDTO> result = LanguageMapper.entitiesToResultDTO(
                languageService.findAll());
        return ResponseEntity.ok(result);
    }
}
