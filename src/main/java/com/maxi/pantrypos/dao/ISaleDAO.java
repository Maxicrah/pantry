package com.maxi.pantrypos.dao;

import com.maxi.pantrypos.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISaleDAO extends JpaRepository<Sale, Long> {
}
