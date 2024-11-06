package com.springsecurity.CustProdOrdePaymentService.model;






import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class Login {
    private String email;
    private String password;


}
