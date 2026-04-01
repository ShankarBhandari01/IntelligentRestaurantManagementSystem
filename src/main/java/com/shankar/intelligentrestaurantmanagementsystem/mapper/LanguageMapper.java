package com.shankar.intelligentrestaurantmanagementsystem.mapper;

import com.shankar.intelligentrestaurantmanagementsystem.dto.LanguageDto;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Language;
import org.springframework.stereotype.Component;

@Component
public class LanguageMapper implements Mapper<Language, LanguageDto, LanguageDto> {
    @Override
    public Language toEntity(LanguageDto dto) {
        if (dto == null) return null;
        return new Language(dto.getEn(), dto.getFi());
    }

    @Override
    public LanguageDto toResponse(Language language) {
        if (language == null) return null;
        return new LanguageDto(language.getEn(), language.getFi());
    }
}
