package com.maxi.pantrypos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@Entity
public class Customer extends BusinessPartner {

//    @Email(message = "email no valid")
//    @NotBlank(message = "email is mandatory")
//    @Column(unique = true)

    @Column(unique = true, nullable = false)
//    @Pattern(regexp = "\\d{8}", message = "el dni debe tener 8 dígitos")
    @OneToMany(mappedBy = "customer")
    private List<Sale> sales;
}
