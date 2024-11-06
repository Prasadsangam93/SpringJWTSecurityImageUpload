package com.springsecurity.CustProdOrdePaymentService.service;


import com.springsecurity.CustProdOrdePaymentService.entity.Customer;
import com.springsecurity.CustProdOrdePaymentService.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public  class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


//    @Autowired
//    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);

    }

    @Override
    public Customer updateCustomer(Long customerId, Customer customerDetails) {


        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setCustomerName(customerDetails.getCustomerName());
        customer.setGender(customerDetails.getGender());
        customer.setEmail(customerDetails.getEmail());
        customer.setPhoneNumber(customerDetails.getPhoneNumber());
        //customer.setAddresses(customerDetails.getAddresses());
        //customer.setProducts(customerDetails.getProducts());
        return customerRepository.save(customer);



}

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();

    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        return customerRepository.findById(customerId);

    }

    @Override
    public Optional<Customer> deleteCustomerById(Long customerId) {


        // Check if the customer exists
        Optional<Customer> customer = customerRepository.findById(customerId);

        if (customer.isPresent()) {
            // If customer exists, delete it
            customerRepository.deleteById(customerId);
            return customer; // Return the deleted customer details
        } else {
            // If customer does not exist, return empty Optional
            return Optional.empty();
        }
    }

}