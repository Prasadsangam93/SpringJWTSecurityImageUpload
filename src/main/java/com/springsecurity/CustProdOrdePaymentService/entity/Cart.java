package com.springsecurity.CustProdOrdePaymentService.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)


    private Long cartId;

    private Integer quantity;
    private Long customerId;  // Renamed to follow Java naming conventions
    private Double price;
    private  String productName;
    private Double totalPrice;



    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "productId")
    private Product product;  // Ensure Product class is defined with a productId field



    // Calculate totalPrice based on quantity and price
    public void calculateTotalPrice() {
        this.totalPrice = this.price * this.quantity;
    }
}


