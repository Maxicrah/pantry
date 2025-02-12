package com.maxi.pantrypos.model;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder(toBuilder = true)
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
    @Lob
    private byte[] imageData;
    private String imageName;
    private String imageType;
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

