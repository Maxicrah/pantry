package com.maxi.pantrypos.model;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProduct;
    private String codProduct;
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
    @ManyToMany(mappedBy = "products")
    private List<Offer> offers;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "products")
    private List<Supplier> suppliers;
}

