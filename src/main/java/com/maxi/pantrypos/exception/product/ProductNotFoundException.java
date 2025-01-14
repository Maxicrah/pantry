package com.maxi.pantrypos.exception.product;

import lombok.Data;

@Data
public class ProductNotFoundException extends RuntimeException {
    private String detail;
    public ProductNotFoundException(String message, String detail) {
        super(message);
        this.detail = detail;
    }
}
