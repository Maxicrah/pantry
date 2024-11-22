package com.maxi.pantrypos.dao;

import com.maxi.pantrypos.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOfferDAO extends JpaRepository<Offer, Long> {

}
