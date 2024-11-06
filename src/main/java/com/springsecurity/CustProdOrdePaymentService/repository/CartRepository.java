package com.springsecurity.CustProdOrdePaymentService.repository;


import com.springsecurity.CustProdOrdePaymentService.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

}