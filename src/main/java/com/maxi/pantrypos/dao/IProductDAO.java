package com.maxi.pantrypos.dao;

import com.maxi.pantrypos.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IProductDAO extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) = LOWER(:name)")
    Product findByName(String name);

    @Query("SELECT p FROM Sale s JOIN s.productsSold p GROUP BY p ORDER BY COUNT(p) DESC")
    Optional<Product> findBestSellingProduct();
}
