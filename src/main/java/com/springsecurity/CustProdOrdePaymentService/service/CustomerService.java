package com.springsecurity.CustProdOrdePaymentService.service;


import com.springsecurity.CustProdOrdePaymentService.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    public Customer saveCustomer(Customer customer);


    public Customer updateCustomer(Long customerId, Customer customerDetails);

    public List<Customer> getAllCustomers();

    public Optional<Customer> getCustomerById(Long customerId);

    public Optional<Customer> deleteCustomerById(Long customerId);
}
