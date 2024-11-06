package com.springsecurity.CustProdOrdePaymentService.controller;


import com.springsecurity.CustProdOrdePaymentService.entity.Cart;
import com.springsecurity.CustProdOrdePaymentService.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService; // Assume this service handles the business logic

    @PostMapping("/add")
    public ResponseEntity<Cart> addCartItem(@RequestParam Long customerId,
                                            @RequestParam Long productId,
                                            @RequestParam Integer quantity) {
        Cart cart = cartService.addCartItem(customerId, productId, quantity);
        return ResponseEntity.ok(cart);
    }


    @DeleteMapping("delete/{cartId}")
    public ResponseEntity<String> deleteCart(@PathVariable Long cartId) {
        // Call the service method to delete the cart item
        String responseMessage = cartService.deleteCart(cartId);
        return ResponseEntity.ok(responseMessage); // Return the response message
    }
}


