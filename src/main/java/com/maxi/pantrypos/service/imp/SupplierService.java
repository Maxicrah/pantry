package com.maxi.pantrypos.service.imp;

import com.maxi.pantrypos.dao.IBusinessPartnerDAO;
import com.maxi.pantrypos.dao.ISupplierDAO;
import com.maxi.pantrypos.dto.SupplierDTO;
import com.maxi.pantrypos.exception.product.ProductNotFoundException;
import com.maxi.pantrypos.model.BusinessPartner;
import com.maxi.pantrypos.model.Product;
import com.maxi.pantrypos.model.Supplier;
import com.maxi.pantrypos.response.ResponseProduct;
import com.maxi.pantrypos.service.IBusinessPartnerService;
import com.maxi.pantrypos.service.IProductService;
import com.maxi.pantrypos.service.ISupplierService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService extends BusinessPartnerService<Supplier, Long, SupplierDTO> implements ISupplierService {

    private final ISupplierDAO supplierDAO;
    private final IProductService productService;

    public SupplierService(ISupplierDAO supplierDAO,
                           IProductService productService) {
        super(supplierDAO);
        this.supplierDAO = supplierDAO;
        this.productService = productService;
    }

    @Override
    @Transactional
    public SupplierDTO saveDTO(SupplierDTO dtoEntity) {
        if (dtoEntity.getResponseProducts() == null || dtoEntity.getResponseProducts().isEmpty()) {
            //cambiar luego por excepcion personalizada
            throw new RuntimeException("supplier must have at least one product");
        }
        if(super.validateDuplicateDni(dtoEntity.getDni())) {
            //cambiar luego por excepcion personalizada
            throw new RuntimeException("supplier already exists");
        }
        if(super.validateDuplicateEmail(dtoEntity.getEmail())) {
            //cambiar luego por excepcion personalizada
            throw new RuntimeException("supplier with: " + dtoEntity.getEmail() + " already exists");
        }
        Supplier supplier = this.buildSupplier(dtoEntity);

        Supplier savedSupplier = this.supplierDAO.save(supplier);

        return convertToDTO(savedSupplier);
    }

@Override
public Supplier save(Supplier entity) {
    if (entity.getProducts() == null || entity.getProducts().isEmpty()) {
        throw new RuntimeException("Supplier must have at least one product");
    }
    List<Product> verifiedProducts = new ArrayList<>();

    for (Product product : entity.getProducts()) {

        Product existingProduct = this.productService.getProduct(product.getIdProduct());

        if (existingProduct == null) {
            throw new ProductNotFoundException("Product not found: " + product.getIdProduct(), "Verify product");
        }

//        ResponseProduct responseProduct = ResponseProduct.builder()
//                .name(existingProduct.getName())
//                .cost(existingProduct.getCost())
//                .codProduct(existingProduct.getCodProduct())
//                .build();

        verifiedProducts.add(existingProduct);
    }

    entity.setProducts(verifiedProducts);

    return this.supplierDAO.save(entity);
}


    @Override
    public Supplier update(Long aLong, Supplier entity) {
        return null;
    }

    @Override
    public Supplier findById(Long aLong) {
        return null;
    }

    @Override
    public Supplier findByDni(String dni) {
        return null;
    }

    @Override
    public List<Supplier> findAll() {
        return this.supplierDAO.findAll();
    }




    @Override
    public void delete(Long aLong) {

    }

    private Supplier buildSupplier(SupplierDTO dtoEntity) {

        List<Product> verifiedProducts = this.convertResponseProductsToProducts(dtoEntity);

        return Supplier.builder()
                .firstName(dtoEntity.getFirstName())
                .lastName(dtoEntity.getLastName())
                .address(dtoEntity.getAddress())
                .phoneNumber(dtoEntity.getPhoneNumber())
                .dni(dtoEntity.getDni())
                .email(dtoEntity.getEmail())
                .bankDetails(dtoEntity.getBankDetails())
                .products(verifiedProducts)
                .build();
    }


    private SupplierDTO convertToDTO(Supplier supplier) {
        List<ResponseProduct> responseProducts = this.convertProductsToResponseProducts(supplier);
        return SupplierDTO.builder()
                .firstName(supplier.getFirstName())
                .lastName(supplier.getLastName())
                .address(supplier.getAddress())
                .phoneNumber(supplier.getPhoneNumber())
                .dni(supplier.getDni())
                .email(supplier.getEmail())
                .bankDetails(supplier.getBankDetails())
                .responseProducts(responseProducts)
                .build();
    }

    private List<Product> convertResponseProductsToProducts(SupplierDTO dtoEntity) {
        List<Product> verifiedProducts = new ArrayList<>();

        for (ResponseProduct responseProduct : dtoEntity.getResponseProducts()) {
            Product existingProduct = productService.getProduct(responseProduct.getIdProduct());

            if (existingProduct == null) {
                throw new ProductNotFoundException("Product not found: " + responseProduct.getIdProduct(), "Verify product");
            }

            verifiedProducts.add(existingProduct);
        }
        return verifiedProducts;
    }

    private List<ResponseProduct> convertProductsToResponseProducts(Supplier supplier) {

        List<ResponseProduct> responseProducts = new ArrayList<>();

        for(Product product : supplier.getProducts()) {
            responseProducts.add(ResponseProduct.builder()
                    .idProduct(product.getIdProduct())
                    .cost(product.getCost())
                    .name(product.getName())
                    .codProduct(product.getCodProduct())
                    .build());
        }
        return responseProducts;
    }


}
