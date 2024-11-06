package com.springsecurity.CustProdOrdePaymentService.entity;







import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ProductTable")
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long productId;

    private String productName;


    private Double price;


    private Long quantity;

    @Lob
    @Column(columnDefinition = "longblob")
    private byte[] image;
}
