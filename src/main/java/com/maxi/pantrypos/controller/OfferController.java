package com.maxi.pantrypos.controller;

import com.maxi.pantrypos.dto.OfferDTO;

import com.maxi.pantrypos.model.Offer;
import com.maxi.pantrypos.service.IOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private IOfferService offerService;

    @PostMapping("/create-multi-product-offer")
    public ResponseEntity<?> createOfferForMultipleProducts(@RequestBody OfferDTO offerDTO) {
        try {
            if (offerDTO.getProductIds() == null || offerDTO.getProductIds().isEmpty()) {
                return ResponseEntity.badRequest().body("product ids must not be empty");
            }
            if (offerDTO.getDiscount() == null || offerDTO.getDiscount() <= 0 || offerDTO.getDiscount() > 100) {
                return ResponseEntity.badRequest().body("discount must be a valid percentage (1-100)");
            }
            offerService.createOfferByTwoOrMoreProducts(offerDTO.getProductIds(), offerDTO.getDiscount());
            return ResponseEntity.ok("offer created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("error occurred while creating the offer");
        }
    }

    @PostMapping("/create-single-product-offer")
    public ResponseEntity<?> createOfferForOneProduct(@RequestBody OfferDTO offerDTO) {
        try {
            if (offerDTO.getProductName() == null || offerDTO.getProductName().isEmpty()) {
                return ResponseEntity.badRequest().body("Product name must not be empty");
            }
            if (offerDTO.getDiscount() == null || offerDTO.getDiscount() <= 0 || offerDTO.getDiscount() > 100) {
                return ResponseEntity.badRequest().body("Discount must be a valid percentage (1-100)");
            }
            if (offerDTO.getMinQuantity() == null || offerDTO.getMinQuantity() <= 0) {
                return ResponseEntity.badRequest().body("Minimum quantity must be greater than 0");
            }

            offerService.createOfferByOneProduct(offerDTO.getProductName(), offerDTO.getMinPurchaseProd(),
                    offerDTO.getDiscount(), offerDTO.getMinQuantity());
            return ResponseEntity.ok("Offer created successfully");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("An error occurred while creating the offer");
        }
    }

    @GetMapping("/")
    public List<Offer> getAllOffers(){
        return this.offerService.getAllOffers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> findOfferById(@PathVariable(name="id") Long id){
        return ResponseEntity.ok(offerService.findOfferById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteOfferById(@PathVariable(name="id") Long id){
        this.offerService.deleteOffer(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "offer deleted successfully"
        ));
    }


}
