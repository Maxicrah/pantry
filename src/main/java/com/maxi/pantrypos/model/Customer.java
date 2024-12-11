package com.maxi.pantrypos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCustomer;
    private String name;
    private String address;
    private String phoneNumber;
    @Email(message = "email no valid")
    @NotBlank(message = "email is mandatory")
    @Column(unique = true)
    private String email;
    @Column(unique = true, nullable = false)
    @Pattern(regexp = "\\d{8}", message = "el dni debe tener 8 dígitos")
    private String dni;
    @OneToMany(mappedBy = "customer")
    private List<Sale> sales;
}
