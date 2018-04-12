package br.com.javanei.i18n.v1.company;

import br.com.javanei.i18n.service.CompanyService;
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
@RequestMapping("/api/v1/companies")
@Api(description = "Company administration", tags = {"Companies"})
public class CompanyRest {
    private CompanyService companyService;

    public CompanyRest(CompanyService companyService) {
        this.companyService = companyService;
    }

    @RequestMapping(method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Add a new company")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = CompanyResponseDTO.class),
            @ApiResponse(code = 400, message = "There was an error creating a company"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 500, message = "Internal server error, see response for more details")
    })
    public ResponseEntity<CompanyResponseDTO> createCompany(@RequestBody CompanyCreateDTO company) {
        CompanyResponseDTO result = CompanyMapper.entityToResponseDTO(
                companyService.create(CompanyMapper.entityFromCreateDTO(company)));
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Find a existing company")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = CompanyResponseDTO.class),
            @ApiResponse(code = 400, message = "There was an error finding a company"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 404, message = "Company not found"),
            @ApiResponse(code = 500, message = "Internal server error, see response for more details")
    })
    public ResponseEntity<CompanyResponseDTO> findCompany(
            @PathVariable("id") String id) {
        CompanyResponseDTO result = CompanyMapper.entityToResponseDTO(
                companyService.findById(id));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Update a existing company")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = CompanyResponseDTO.class),
            @ApiResponse(code = 400, message = "There was an error updating a company"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 404, message = "Company not found"),
            @ApiResponse(code = 500, message = "Internal server error, see response for more details")
    })
    public ResponseEntity<CompanyResponseDTO> updateCompany(
            @PathVariable("id") String id,
            @RequestBody CompanyUpdateDTO company) {
        CompanyResponseDTO result = CompanyMapper.entityToResponseDTO(
                companyService.update(CompanyMapper.entityFromUpdateDTO(id, company)));
        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("Delete a existing company")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = CompanyResponseDTO.class),
            @ApiResponse(code = 400, message = "There was an error deleting a company"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 404, message = "Company not found"),
            @ApiResponse(code = 500, message = "Internal server error, see response for more details")
    })
    public ResponseEntity<CompanyResponseDTO> deleteCompany(
            @PathVariable("id") String id) {
        companyService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation("List companies")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Ok", response = List.class),
            @ApiResponse(code = 400, message = "There was an error listing companies"),
            @ApiResponse(code = 401, message = "User is not authenticated"),
            @ApiResponse(code = 500, message = "Internal server error, see response for more details")
    })
    public ResponseEntity<List<CompanyResponseDTO>> findCompanies() {
        List<CompanyResponseDTO> result = CompanyMapper.entitiesToResultDTO(
                companyService.findAll());
        return ResponseEntity.ok(result);
    }
}
