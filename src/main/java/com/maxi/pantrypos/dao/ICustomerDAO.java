package com.maxi.pantrypos.dao;

import com.maxi.pantrypos.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICustomerDAO extends JpaRepository<Customer, Long> {

}
