package com.maxi.pantrypos.service;

import com.maxi.pantrypos.model.Customer;

import java.util.List;

public interface ICustomerService {
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer, Long id);
    Customer findCustomerById(Long id);
    List<Customer> findAllCustomers();
    void deleteCustomer(Long id);
    Customer findCustomerByName(String name);
}
