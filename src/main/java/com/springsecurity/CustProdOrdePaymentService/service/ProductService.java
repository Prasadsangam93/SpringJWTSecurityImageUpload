package com.springsecurity.CustProdOrdePaymentService.service;


import com.springsecurity.CustProdOrdePaymentService.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {


    Product saveProduct(String productName, Double price, Long quantity, MultipartFile imageFile) throws IOException;
}