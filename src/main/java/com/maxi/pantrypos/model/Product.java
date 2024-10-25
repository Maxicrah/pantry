package com.maxi.pantrypos.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
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
    //private Category category;
    //private Offer offer;
   // private Supplier supplier;
}

