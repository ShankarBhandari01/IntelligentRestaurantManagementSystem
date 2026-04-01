package com.shankar.intelligentrestaurantmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LanguageDto {
    @NotBlank
    private String en;
    @NotBlank
    private String fi;
}