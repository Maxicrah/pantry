package com.maxi.pantrypos.service;

import com.maxi.pantrypos.model.Offer;

import java.util.List;

public interface IOfferService {

    public Offer createOfferByTwoOrMoreProducts( List<Long> productsIds,
                                                Double discount);
    public Offer createOfferByOneProduct(String productName,
                                         Integer minPurchaseProduct,
                                         Double discountPercentage,Integer minQuantity);
    public void deleteOffer(Long id);
    public List<Offer> getAllOffers();
    public Offer findOfferById(Long id);
    public void updateOffer(Offer offer, Long id);

}
