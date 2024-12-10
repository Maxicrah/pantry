package com.maxi.pantrypos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;
    @NotBlank(message = "Phone number is required")
    @Size(max = 10, message = "Phone number must be less than 10 numbers")
    private String phoneNumber;
    @NotBlank(message = "Address is required")
    @Size(max = 200, message = "Address must be less than 200 characters")
    private String address;
    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;
    @NotBlank(message = "Bank details are required")
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
