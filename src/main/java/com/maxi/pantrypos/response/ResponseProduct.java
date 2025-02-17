package com.maxi.pantrypos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseProduct {
    private Long idProduct;
    private Double cost;
    private String name;
    private String codProduct;
}
