package com.maxi.pantrypos.exception.offer;

import lombok.Data;

@Data
public class InvalidOfferException extends RuntimeException {
  private String detail;
    public InvalidOfferException(String message, String detail) {
        super(message);
        this.detail = detail;
    }
}
