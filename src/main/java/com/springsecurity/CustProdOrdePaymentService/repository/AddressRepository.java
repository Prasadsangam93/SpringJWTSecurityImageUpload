package com.springsecurity.CustProdOrdePaymentService.repository;


import com.springsecurity.CustProdOrdePaymentService.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}
