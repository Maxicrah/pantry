package com.maxi.pantrypos.dao;

import com.maxi.pantrypos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductDAO extends JpaRepository<Product, Long> {

}
