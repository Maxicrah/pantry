package com.maxi.pantrypos.exception.product;

import lombok.Data;

@Data
public class ProductCodeException extends RuntimeException {
    private String detail;

    public ProductCodeException(String message, String detail) {
        super(message);
        this.detail = detail;
    }
}
