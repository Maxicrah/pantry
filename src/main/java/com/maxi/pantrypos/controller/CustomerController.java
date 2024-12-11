package com.maxi.pantrypos.controller;

import com.maxi.pantrypos.model.Customer;
import com.maxi.pantrypos.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;


    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> createCustomer(@RequestBody @Valid Customer customer) {
        Customer createdCustomer = this.customerService.createCustomer(customer);
        return ResponseEntity.status(201).body(Map.of(
                "message", "customer created successfully",
                "customer", createdCustomer
        ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findCustomerById(@PathVariable Long id) {
        Customer customer = this.customerService.findCustomerById(id);
        return ResponseEntity.ok(Map.of(
                "message", "customer found",
                "customer", customer
        ));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> findAllCustomers() {
        List<Customer> customers = this.customerService.findAllCustomers();
        return ResponseEntity.ok(customers);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteCustomer(@PathVariable Long id) {
        this.customerService.deleteCustomer(id);
        return ResponseEntity.ok(Map.of(
                "message", "customer deleted successfully",
                "customerId", id
        ));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Map<String, Object>> updateCustomer(
            @RequestBody @Valid Customer customer,
            @PathVariable Long id) {
        Customer updatedCustomer = this.customerService.updateCustomer(customer, id);
        return ResponseEntity.ok(Map.of(
                "message", "customer updated successfully",
                "customer", updatedCustomer
        ));
    }

}
