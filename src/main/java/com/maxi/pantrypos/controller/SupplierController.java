package com.maxi.pantrypos.controller;

import com.maxi.pantrypos.dto.SupplierDTO;
import com.maxi.pantrypos.model.Supplier;
import com.maxi.pantrypos.service.ISupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/supplier")
public class SupplierController {

    private final ISupplierService supplierService;

    @Autowired
    public SupplierController(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> findSupplierById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.supplierService.findById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<SupplierDTO> createSupplier(@RequestBody @Valid SupplierDTO supplier) {
        SupplierDTO savedSupplier = this.supplierService.saveDTO(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSupplier);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = this.supplierService.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(suppliers);
    }

//    @GetMapping("/find-by-name/{name}")
//    public ResponseEntity<Supplier> findSupplierByName(@PathVariable(name = "name") String name) {
//        Supplier supplier = this.supplierService.findSupplierByName(name);
//        if (supplier == null) {
//            throw new IllegalArgumentException("supplier with name " + name + " not found");
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(supplier);
//    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable(name = "id") Long id) {
        Supplier supplier = this.supplierService.findById(id);
        if (supplier == null) {
            throw new IllegalArgumentException("supplier with ID " + id + " not found");
        }
        this.supplierService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
