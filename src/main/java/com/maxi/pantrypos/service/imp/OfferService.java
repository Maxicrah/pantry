package com.maxi.pantrypos.service.imp;

import com.maxi.pantrypos.dao.IOfferDAO;

import com.maxi.pantrypos.model.Offer;
import com.maxi.pantrypos.model.Product;
import com.maxi.pantrypos.service.IOfferService;
import com.maxi.pantrypos.service.IProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OfferService implements IOfferService {

    private final IOfferDAO offerDAO;
    private final IProductService productService;

    public OfferService(IOfferDAO offerDAO, IProductService productService) {
        this.productService = productService;
        this.offerDAO = offerDAO;
    }

    @Override
    public Offer createOfferByTwoOrMoreProducts(List<Long> productsIds, Double discount) {
        Offer offer = new Offer();
        //traemos a los productos que deseamos añadir ala oferta
        List<Product> products = this.productService.findProductsByIds(productsIds);
        Double totalPriceOffer= 0.0;
        //le asignamos oferta y le cambiamos el offer a true
        offer.setProducts(products);
        for(Product product: products) {
            product.setIsOnSale(true);
            totalPriceOffer += product.getPrice();
        }
        if(discount > 75){
            throw new RuntimeException("discount must not be greater than 75");
        }
        Double discountedPrice = totalPriceOffer * (1 - discount / 100);
        offer.setDiscount(discount);
        offer.setOfferPrice(discountedPrice);
        offer.setStartDate(LocalDate.now());
        offer.setIsActive(true);
        //guardamo en la base de datos
        return this.offerDAO.save(offer);
    }

    @Override
    public Offer createOfferByOneProduct(String productName,Integer minPurchaseProduct,
                                          Double discountPercentage,Integer minQuantity) {
        Offer offer = new Offer();
        Product product = this.productService.findProductByName(productName);

        if (product == null) {
            throw new RuntimeException("product not found");
        }
        if(this.productService.validateOffer(product.getIdProduct())){
                throw new RuntimeException("offer already exists");
        }

        product.setIsOnSale(true);

        List<Product> products = new ArrayList<>();
        products.add(product);

        offer.setProducts(products);

        offer.setMinPurchaseProd(minPurchaseProduct);

        if (minPurchaseProduct > minQuantity) {
            Double totalPrice = product.getPrice() * minPurchaseProduct;
            Double discountAmount = totalPrice * (discountPercentage / 100);
            Double discountedPrice = totalPrice - discountAmount;

            offer.setOfferPrice(discountedPrice);
        } else {
            throw new RuntimeException("minimum purchase quantity should be greater than 0");
        }

        return this.offerDAO.save(offer);
    }

    @Override
    public void deleteOffer(Long id) {
        if(this.findOfferById(id) == null) {
            throw new RuntimeException("no offer found with id: " + id);
        }
        this.offerDAO.deleteById(id);
    }

    @Override
    public List<Offer> getAllOffers() {
        return this.offerDAO.findAll();
    }

    @Override
    public Offer findOfferById(Long id) {
        return this.offerDAO.findById(id).orElse(null);
    }

    @Override
    public void updateOffer(Offer offer, Long id) {

    }
}
