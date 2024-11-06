package com.springsecurity.CustProdOrdePaymentService.entity;






import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CustomerTable")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;


    private String customerName;

    private String gender;

    private String email;

    private Long phoneNumber;
    private String password;

    private String abn;
    private String companyAddress;
    private String state;
    private String postalCode;


}



