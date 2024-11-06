package com.springsecurity.CustProdOrdePaymentService.controller;


import com.springsecurity.CustProdOrdePaymentService.entity.Customer;
import com.springsecurity.CustProdOrdePaymentService.exception.UnAuthorizedExceptionCls;
import com.springsecurity.CustProdOrdePaymentService.model.Login;
import com.springsecurity.CustProdOrdePaymentService.service.CustomerService;
import com.springsecurity.CustProdOrdePaymentService.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private LoginService loginService;

    @Autowired
    private PasswordEncoder encoder;


    @PostMapping("/save")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {

        customer.setPassword(encoder.encode(customer.getPassword()));
        Customer savedCustomer = customerService.saveCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    // Update an existing customer
    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customerId, customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    // Fetch all customers
    @GetMapping("/getAll")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // Fetch a customer by ID
    @GetMapping("/{customerId}")

    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Optional<Customer> customer = customerService.getCustomerById(customerId);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Customer> deleteCustomerById(@PathVariable Long customerId) {
        Optional<Customer> customer = customerService.deleteCustomerById(customerId);
        return customer.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }




    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login) {
        try {
            String token = loginService.tokenGenerateJwt(login);
            return ResponseEntity.ok(token); // Return token if authentication is successful
        } catch (UnAuthorizedExceptionCls e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage()); // Return 401 for unauthorized access
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An internal error occurred.");


        }

    }
}





