package com.springsecurity.CustProdOrdePaymentService.service;


import com.springsecurity.CustProdOrdePaymentService.entity.Product;
import com.springsecurity.CustProdOrdePaymentService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    /**
     * Save a product along with its image (byte array).
     */
    public Product saveProduct(String productName, Double price, Long quantity, MultipartFile imageFile) throws IOException {
        // Convert the image to a byte array
        byte[] imageBytes = imageFile.getBytes();

        // Create a new Product instance
        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setImage(imageBytes); // Save the image as a byte array

        // Save the product to the database
        return productRepository.save(product);
    }
}



