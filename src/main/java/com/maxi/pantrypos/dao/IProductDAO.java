package com.maxi.pantrypos.dao;

import com.maxi.pantrypos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductDAO extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name)")
    public Product findByName(String name);
}
