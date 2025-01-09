package com.maxi.pantrypos.dto;

import com.maxi.pantrypos.model.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class OfferDTO {
    @NotBlank(message = "offer title is required")
    private String title;
    private String description;
    @NotNull(message = "expiration date is required")
    private LocalDate expirationDate;
    @NotNull(message = "discount is required")
    @Min(value = 0,message = "discount must be positive")
    private Double discount;
    @NotNull(message = "start date is required")
    private LocalDate startDate;
    private Boolean isActive;
    @NotNull(message = "products list is not be null")
    @Size(min=1, message = "at least one product is required")
    private List<Long> productIds;
    @NotBlank(message = "product name is required")
    private String productName;
    @NotNull(message = "min purchase product is required")
    private Integer minPurchaseProd;
    @NotNull(message = "min quantity is required")
    @Min(value = 1, message = "at least one product is required")
    private Integer minQuantity;


}
