package com.maxi.pantrypos.dto;

import com.maxi.pantrypos.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class OfferDTO {

    private String title;
    private String description;
    private LocalDate expirationDate;
    private Double discount;
    private LocalDate startDate;
    private Boolean isActive;
    private List<Long> productIds;
    private String productName;
    private Integer minPurchaseProd;
    private Integer minQuantity;


}
