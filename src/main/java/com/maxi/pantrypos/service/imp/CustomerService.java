package com.maxi.pantrypos.service.imp;

import com.maxi.pantrypos.dao.ICustomerDAO;
import com.maxi.pantrypos.model.Customer;
import com.maxi.pantrypos.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private ICustomerDAO customerDAO;


    @Override
    public Customer createCustomer(Customer customer) {
        if(this.customerDAO.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("email already exists");
        }
        if(this.customerDAO.existsByDni(customer.getDni())) {
            throw new RuntimeException("dni already exists");
        }
        return this.customerDAO.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer, Long id) {
        Customer existingCustomer = this.customerDAO.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("customer not found"));

        if (!existingCustomer.getEmail().equals(customer.getEmail())
                && customerDAO.existsByEmail(customer.getEmail())) {
            throw new IllegalArgumentException("Email is already in use");
        }

        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setPhoneNumber(customer.getPhoneNumber());
        return this.customerDAO.save(existingCustomer);
    }


    @Override
    public Customer findCustomerById(Long id) {
        return this.customerDAO.findById(id).orElseThrow(() ->
                new IllegalArgumentException("customer not found"));
    }

    @Override
    public List<Customer> findAllCustomers() {
        return this.customerDAO.findAll();
    }

    @Override
    public void deleteCustomer(Long id) {
       Customer customer = this.findCustomerById(id);
       if(customer == null) {
           throw new RuntimeException("customer not found");
       }
       this.customerDAO.delete(customer);
    }

    @Override
    public Customer findCustomerByName(String name) {
        return this.customerDAO.findByName(name).orElseThrow(() ->
                new IllegalArgumentException("customer not found"));
    }



}
