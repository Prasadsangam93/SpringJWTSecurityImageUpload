package com.springsecurity.CustProdOrdePaymentService.controller;


import com.springsecurity.CustProdOrdePaymentService.entity.Product;
import com.springsecurity.CustProdOrdePaymentService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/products")
public class ProductController {



    @Autowired
    private ProductService productService;

    @PostMapping("/save")
    public ResponseEntity<String> saveProduct(
            @RequestParam("productName") String productName,
            @RequestParam("price") Double price,
            @RequestParam("quantity") Long quantity,
            @RequestParam("image") MultipartFile image) {
        try {
            // Call the service to save the product
            Product savedProduct = productService.saveProduct(productName, price, quantity, image);

            return ResponseEntity.ok("Product saved successfully with ID: " + savedProduct.getProductId());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving product: " + e.getMessage());
        }
    }
}

