package com.springsecurity.CustProdOrdePaymentService.exception;





import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadRequestCls extends Throwable {

    private  String errorMessage;

}
