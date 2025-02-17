package com.maxi.pantrypos.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@MappedSuperclass
@Getter @Setter
@SuperBuilder
@AllArgsConstructor @NoArgsConstructor
public class BusinessPartner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long businessPartnerId;
    @NotBlank(message = "first name is required")
    @Size(min = 5, message = "first name must be more than 5 characters")
    private String firstName;
    @NotBlank(message = "last name is required")
    @Size(min = 5, message = "last name must be more than 5 characters")
    private String lastName;
    @NotBlank(message = "address is required")
    @Size(min = 5, message = "address must be more than 5 characters")
    private String address;
    @Pattern(regexp = "\\d{10,15}", message = "Phone number must be between 10 and 15 digits")
    private String phoneNumber;
    @NotBlank(message = "dni is required")
    @Size(min = 7, max = 8, message = "dni must be between 7 and 8 digits")
    @Pattern(regexp = "\\d{7,8}", message = "DNI must contain only numbers and be 7 to 8 digits long")
    private String dni;
    @Email(message = "email should be valid")
    private String email;

}
