package com.maxi.pantrypos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class ProductDTO {

    private String name;
    private String description;
    private LocalDate expirationDate;
    private LocalDate entryDate;
    private Double price;
    private String image;
    private Long stock;
    private Double cost;
    private Boolean isOnSale;
    private Double unitOfMeasure;
}



