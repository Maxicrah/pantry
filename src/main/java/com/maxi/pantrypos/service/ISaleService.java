package com.maxi.pantrypos.service;

import com.maxi.pantrypos.model.Sale;

import java.util.List;

public interface ISaleService {

    public Sale processSale(Sale sale, Long idCustomer);
    public Sale getSaleById(Long id);
    public void deleteSaleById(Long id);
    public List<Sale> getAllSales();
    public Sale updateSale(Sale sale, Long id);
}
