package com.maxi.pantrypos.service.imp;

import com.maxi.pantrypos.dao.ISaleDAO;
import com.maxi.pantrypos.model.Product;
import com.maxi.pantrypos.model.Sale;
import com.maxi.pantrypos.service.IProductService;
import com.maxi.pantrypos.service.ISaleService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service
public class SaleService implements ISaleService {
    private final ISaleDAO saleDAO;
   // private final IProductService productService;
    public SaleService(ISaleDAO saleDAO) {
        //this.productService = productService;
        this.saleDAO = saleDAO;
    }

    @Override
    public Sale processSale(Sale sale) {
        if (sale.getProductsSold() == null || sale.getProductsSold().isEmpty()) {
            throw new RuntimeException("not products sold");
        }
        Double totalAmount = 0.0;
        for(Product product : sale.getProductsSold()) {
            totalAmount += product.getPrice();
        }
        sale.setTotalAmount(totalAmount);
        sale.setSaleDate(LocalDateTime.now());
        sale.setStatus("completed");
        return this.saleDAO.save(sale);
    }

    @Override
    public Sale getSaleById(Long id) {
        return this.saleDAO.findById(id).orElse(null);
    }

    @Override
    public void deleteSaleById(Long id) {
        if (!this.saleDAO.existsById(id)) {
            throw new RuntimeException("Sale not found");
        }
        this.saleDAO.deleteById(id);
    }

    @Override
    public List<Sale> getAllSales() {
        return this.saleDAO.findAll();
    }

    @Override
    public Sale updateSale(Sale sale, Long id) {
        Sale newSale = this.getSaleById(id);
        if(newSale == null) {
            throw new RuntimeException("sale not found");
        }
        newSale.setProductsSold(sale.getProductsSold());
        Double totalAmount = 0.0;
        for(Product product : newSale.getProductsSold()) {
            totalAmount += product.getPrice();
        }
        newSale.setTotalAmount(totalAmount);
        newSale.setSaleDate(LocalDateTime.now());
        newSale.setStatus(sale.getStatus());
        return this.saleDAO.save(newSale);
    }

}
