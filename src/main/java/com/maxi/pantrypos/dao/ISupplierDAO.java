package com.maxi.pantrypos.dao;

import com.maxi.pantrypos.model.Supplier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISupplierDAO extends IBusinessPartnerDAO<Supplier,Long> {


}
