package com.maxi.pantrypos.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ProductDTO {
    @NotBlank(message = "product name is required")
    private String name;
    @NotBlank(message = "code product is required")
    @Size(min = 6, message = "code product must be more than 6 characters")
    private String codProduct;
    private String description;
    @NotNull
    private LocalDate expirationDate;
    private LocalDate entryDate;
    @NotNull(message = "price is required")
    @Min(value = 20,message = "price must be minor 20")
    private Double price;
    private String image;
    @NotNull(message = "stock is required")
    @Min(value = 1,message = "min stock 1")
    @Max(value = 100, message = "stock no more than 100")
    private Long stock;
    @NotNull(message = "cost is required")
    @Min(value = 1, message = "cost must be at least 1")
    private Double cost;
    private Boolean isOnSale;
    private Double unitOfMeasure;
}



