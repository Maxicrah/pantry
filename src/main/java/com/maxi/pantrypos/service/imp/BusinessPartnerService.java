package com.maxi.pantrypos.service.imp;

import com.maxi.pantrypos.dao.IBusinessPartnerDAO;
import com.maxi.pantrypos.model.BusinessPartner;
import com.maxi.pantrypos.service.IBusinessPartnerService;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.util.List;

public abstract class BusinessPartnerService<E extends BusinessPartner, ID extends Serializable, DTO extends BusinessPartner> implements IBusinessPartnerService<E, ID, DTO> {

    protected IBusinessPartnerDAO<E, ID> personDAO;

    public BusinessPartnerService(IBusinessPartnerDAO<E, ID> personDAO) {
        this.personDAO = personDAO;
    }

    @Transactional
    public E save(E entity) {
        return this.personDAO.save(entity);
    }
    @Transactional
    public E update(ID id, E entity){

        E e = this.personDAO.findById(id).orElse(null);
       return e = this.personDAO.save(entity);

    }

    public E findById(ID id){
        return this.personDAO.findById(id).orElseThrow(()->
                new RuntimeException("Invalid ID"));
    }

    public List<E> findAll(){
        return this.personDAO.findAll();
    }

    public E findByDni(String dni){
       E entity = this.personDAO.findByDni(dni);
       if(entity == null){
           throw new RuntimeException("Dni not found");
       }
       return entity;
    }


    public void delete(ID id){
        this.personDAO.deleteById(id);
    }

    protected boolean validateDuplicateEmail(String email){
        List<E> businessPartners = this.findAll();
        for(E businessPartner : businessPartners){
            if(businessPartner.getEmail().equals(email)){
                return true;
            }
        }
        return false;
    }

    protected boolean validateDuplicateDni(String dni){

        List<E> businessPartners = this.findAll();
        for(E businessPartner : businessPartners){
            if(businessPartner.getDni().equals(dni)){
                return true;
            }
        }
        return false;
    }


}
