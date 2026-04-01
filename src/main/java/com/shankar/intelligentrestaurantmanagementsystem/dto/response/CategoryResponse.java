package com.shankar.intelligentrestaurantmanagementsystem.dto.response;

import com.shankar.intelligentrestaurantmanagementsystem.dto.LanguageDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryResponse {
    private String id;

    private LanguageDto name;
    private LanguageDto description;
    private LanguageDto remarks;

    private Boolean isLunchCategory;
    private Boolean isOrderable;
    private Boolean isActive;
    private Boolean isDeleted;
}
