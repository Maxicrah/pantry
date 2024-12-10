package com.maxi.pantrypos.service.imp;

import com.maxi.pantrypos.dao.ISupplierDAO;
import com.maxi.pantrypos.model.Supplier;
import com.maxi.pantrypos.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SupplierService implements ISupplierService {

    private final ISupplierDAO supplierDAO;

    @Autowired
    public SupplierService(ISupplierDAO supplierDAO) {

        this.supplierDAO = supplierDAO;
    }


    @Override
    public Supplier saveSupplier(Supplier supplier) {
        if (supplier == null || supplier.getName() == null || supplier.getName().isBlank()) {
            throw new IllegalArgumentException("a supplier name is required");
        }

        if (supplierDAO.existsByName(supplier.getName())) {
            throw new IllegalArgumentException("a supplier with the same name already exists");
        }
        return this.supplierDAO.save(supplier);
    }

    @Override
    public Supplier findSupplierById(Long supplierId) {
        return this.supplierDAO.findById(supplierId).orElse(null);
    }

    @Override
    public void deleteSupplier(Long supplierId) {
        if(this.findSupplierById(supplierId) == null) {
            throw new IllegalArgumentException("a supplier no exists");
        }
        this.supplierDAO.deleteById(supplierId);
    }

    @Override
    public List<Supplier> findAllSuppliers() {
        return this.supplierDAO.findAll();
    }

    @Override
    public Supplier findSupplierByName(String supplierName) {
        return this.supplierDAO.findByName(supplierName).orElse(null);
    }

    @Override
    public Supplier updateSupplier(Supplier newSupplier, Long id) {
        Supplier oldSupplier = this.findSupplierById(id);
        if (oldSupplier == null) {
            throw new IllegalArgumentException("a supplier not found");
        }

        if (newSupplier.getName() != null){
            oldSupplier.setName(newSupplier.getName());
        }
        if (newSupplier.getAddress() != null){
            oldSupplier.setAddress(newSupplier.getAddress());
        }
        if (newSupplier.getEmail() != null){
            oldSupplier.setEmail(newSupplier.getEmail());
        }
        if (newSupplier.getBankDetails() != null){
            oldSupplier.setBankDetails(newSupplier.getBankDetails());
        }
        if (newSupplier.getProducts() != null){
            oldSupplier.setProducts(newSupplier.getProducts());
        }

        oldSupplier.setUpdatedAt(LocalDate.now());
        return this.supplierDAO.save(oldSupplier);
    }
}
