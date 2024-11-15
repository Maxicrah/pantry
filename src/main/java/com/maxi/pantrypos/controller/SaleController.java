package com.maxi.pantrypos.controller;

import com.maxi.pantrypos.model.Sale;
import com.maxi.pantrypos.service.ISaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sale")
public class SaleController {

    @Autowired
    private ISaleService saleService;

    @PostMapping("/create")
    public ResponseEntity<Sale> processSale(@RequestBody Sale sale){
        this.saleService.processSale(sale);
        return ResponseEntity.ok(sale);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSale(@PathVariable(name= "id") Long id){
        this.saleService.deleteSaleById(id);
        return ResponseEntity.ok("Sale deleted");
    }

    @GetMapping("find/{id}")
    public ResponseEntity<Sale> findSale(@PathVariable(name = "id") Long id){
        Sale sale = this.saleService.getSaleById(id);
        return ResponseEntity.ok(sale);
    }

    @GetMapping("find/all")
    public ResponseEntity<List<Sale>> findAllSale(){
        this.saleService.getAllSales();
        return ResponseEntity.ok(this.saleService.getAllSales());
    }

}
