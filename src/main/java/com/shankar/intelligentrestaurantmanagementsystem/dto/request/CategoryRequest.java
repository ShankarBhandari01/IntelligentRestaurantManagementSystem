package com.shankar.intelligentrestaurantmanagementsystem.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryRequest {
    @Valid
    @NotNull
    private LanguageDto name;
    @Valid
    private LanguageDto description;
    @Valid
    private LanguageDto remarks;

    private Boolean isLunchCategory;
    private Boolean isOrderable;
    private Boolean isActive;
    private Boolean isDeleted;
}
