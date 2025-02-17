package com.maxi.pantrypos.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@SuperBuilder
@Entity
public class Supplier extends BusinessPartner {

    private String bankDetails;
    private String createdBy;
    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;
    private LocalDate updatedAt;
    @ManyToMany
    @JoinTable(name = "supplier_product",
                joinColumns = @JoinColumn(
                        name = "id_supplier"
                ),
                inverseJoinColumns = @JoinColumn(
                        name = "id_product"
                )
    )
    private List<Product> products;

}
