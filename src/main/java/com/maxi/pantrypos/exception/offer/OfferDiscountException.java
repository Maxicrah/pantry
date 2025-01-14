package com.maxi.pantrypos.exception.offer;

import lombok.Data;

@Data
public class OfferDiscountException extends RuntimeException {
    private String detail;
    public OfferDiscountException(String message, String detail) {
        super(message);
        this.detail = detail;
    }
}
