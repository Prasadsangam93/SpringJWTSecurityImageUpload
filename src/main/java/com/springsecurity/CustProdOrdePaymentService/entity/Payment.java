package com.springsecurity.CustProdOrdePaymentService.entity;






import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PaymentDetails")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    private Double price;
    private String currency;
    private String paymentMethod;
    private Instant orderDate;
    private String status;


}
