package com.springsecurity.CustProdOrdePaymentService.service;


import com.springsecurity.CustProdOrdePaymentService.entity.Cart;
import com.springsecurity.CustProdOrdePaymentService.entity.Product;
import com.springsecurity.CustProdOrdePaymentService.exception.ResourceNotFoundException;
import com.springsecurity.CustProdOrdePaymentService.repository.CartRepository;
import com.springsecurity.CustProdOrdePaymentService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {


    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Cart addCartItem(Long customerId, Long productId, Integer quantity) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        // Create a new Cart instance
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        cart.setProduct(product);
        cart.setQuantity(quantity);
        cart.setProductName(product.getProductName());
        cart.setPrice(product.getPrice()); // Assuming Product has a getPrice() method
        cart.calculateTotalPrice(); // Calculate total price

        // Save the cart to the database
        return cartRepository.save(cart);
    }

    @Override
    public String deleteCart(Long cartId) {

        if (cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
            return "Cart item successfully deleted with ID: " + cartId; // Return success message
        } else {
            return ("Cart item not found with id: " + cartId); // This will lead to a 500 error
        }

    }


}




