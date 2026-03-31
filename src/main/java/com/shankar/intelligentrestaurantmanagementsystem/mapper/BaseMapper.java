package com.shankar.intelligentrestaurantmanagementsystem.mapper;

import com.shankar.intelligentrestaurantmanagementsystem.dto.request.LanguageDto;
import com.shankar.intelligentrestaurantmanagementsystem.entity.Language;
import org.springframework.stereotype.Component;

@Component
public class BaseMapper {
    Language mapToLanguage(LanguageDto dto) {
        if (dto == null) return null;
        return new Language(dto.getEn(), dto.getFi());
    }

    LanguageDto mapToLanguageDto(Language language) {
        if (language == null) return null;
        return new LanguageDto(language.getEn(), language.getFi());
    }
}
