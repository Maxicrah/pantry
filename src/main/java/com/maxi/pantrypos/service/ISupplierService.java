package com.maxi.pantrypos.service;

import com.maxi.pantrypos.model.Supplier;

import java.util.List;

public interface ISupplierService {

    public Supplier saveSupplier(Supplier supplier);
    public Supplier findSupplierById(Long supplierId);
    public void deleteSupplier(Long supplierId);
    public List<Supplier> findAllSuppliers();
    public Supplier findSupplierByName(String supplierName);
    public Supplier updateSupplier(Supplier newSupplier, Long id);
}
