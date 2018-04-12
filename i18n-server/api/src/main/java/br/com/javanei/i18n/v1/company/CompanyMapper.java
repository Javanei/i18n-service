package br.com.javanei.i18n.v1.company;

import br.com.javanei.i18n.entity.Company;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class CompanyMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    private CompanyMapper() {
    }

    public static CompanyResponseDTO entityToResponseDTO(Company entity) {
        return modelMapper.map(entity, CompanyResponseDTO.class);
    }

    public static Company entityFromCreateDTO(CompanyCreateDTO dto) {
        return modelMapper.map(dto, Company.class);
    }

    public static Company entityFromUpdateDTO(String id, CompanyUpdateDTO dto) {
        Company entity = modelMapper.map(dto, Company.class);
        entity.setId(id);
        return entity;
    }

    public static List<CompanyResponseDTO> entitiesToResultDTO(List<Company> entities) {
        Type targetListType = new TypeToken<List<CompanyResponseDTO>>() {
        }.getType();
        return modelMapper.map(entities, targetListType);
    }
}
