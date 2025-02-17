package com.maxi.pantrypos.service;

import com.maxi.pantrypos.model.BusinessPartner;

import java.io.Serializable;
import java.util.List;

public interface IBusinessPartnerService<E extends BusinessPartner, ID extends Serializable, DTO extends BusinessPartner> {

    DTO saveDTO(DTO dtoEntity);
    E save(E entity);
    E update(ID id,E entity);
    E findById(ID id);
    E findByDni(String dni);
    List<E> findAll();
    void delete(ID id);


}
