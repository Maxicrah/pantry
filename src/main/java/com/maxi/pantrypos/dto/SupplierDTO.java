package com.maxi.pantrypos.dto;

import com.maxi.pantrypos.model.BusinessPartner;
import com.maxi.pantrypos.response.ResponseProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class SupplierDTO extends BusinessPartner {
    private String bankDetails;
    private List<ResponseProduct> responseProducts;
}
