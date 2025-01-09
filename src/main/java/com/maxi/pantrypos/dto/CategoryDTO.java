package com.maxi.pantrypos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor @NoArgsConstructor
@Data
public class CategoryDTO {
    @NotBlank(message = "category name not blank please")
    @Size(min = 5, max = 20, message = "category name min 5 characters, max 20 characters")
    private String categoryName;
}
