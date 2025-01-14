package com.maxi.pantrypos.exception.product;

import lombok.Data;

@Data
public class ProductEntryDateException extends RuntimeException {
    private String detail;
    public ProductEntryDateException(String message, String detail) {
        super(message);
        this.detail = detail;
    }
}
