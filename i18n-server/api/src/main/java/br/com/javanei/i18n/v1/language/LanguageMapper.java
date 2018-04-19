package br.com.javanei.i18n.v1.language;

import br.com.javanei.i18n.entity.Language;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class LanguageMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    private LanguageMapper() {
    }

    public static LanguageResponseDTO entityToResponseDTO(Language entity) {
        return modelMapper.map(entity, LanguageResponseDTO.class);
    }

    public static Language entityFromCreateDTO(LanguageCreateDTO dto) {
        return modelMapper.map(dto, Language.class);
    }

    public static Language entityFromUpdateDTO(String id, LanguageUpdateDTO dto) {
        Language entity = modelMapper.map(dto, Language.class);
        entity.setId(id);
        return entity;
    }

    public static List<LanguageResponseDTO> entitiesToResultDTO(List<Language> entities) {
        Type targetListType = new TypeToken<List<LanguageResponseDTO>>() {
        }.getType();
        return modelMapper.map(entities, targetListType);
    }
}
