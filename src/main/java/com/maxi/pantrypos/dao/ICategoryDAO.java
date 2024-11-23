package com.maxi.pantrypos.dao;

import com.maxi.pantrypos.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoryDAO extends JpaRepository<Category, Long> {
}
