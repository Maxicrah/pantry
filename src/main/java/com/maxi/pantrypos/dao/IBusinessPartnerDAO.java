package com.maxi.pantrypos.dao;

import com.maxi.pantrypos.model.BusinessPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface IBusinessPartnerDAO<E extends BusinessPartner, ID extends Serializable> extends JpaRepository<E, ID> {
    E findByDni(String dni);
}
