package com.springsecurity.CustProdOrdePaymentService.service;


import com.springsecurity.CustProdOrdePaymentService.entity.Cart;

public interface CartService {


    // public Cart createCart(Cart cart);

    public Cart addCartItem(Long customerId, Long productId, Integer quantity);




    public String deleteCart(Long cartId);
}