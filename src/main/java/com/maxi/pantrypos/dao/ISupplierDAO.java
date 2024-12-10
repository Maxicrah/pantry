package com.maxi.pantrypos.dao;

import com.maxi.pantrypos.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ISupplierDAO extends JpaRepository<Supplier,Long> {
    boolean existsByName(String name);

    @Query("SELECT s FROM Supplier s WHERE LOWER(s.name) = LOWER(:name)")
    Optional<Supplier> findByName(String name);
}
