package com.maxi.pantrypos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long idCategory;
    private String categoryName;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,
    fetch = FetchType.LAZY )
    private List<Product> products;
}
