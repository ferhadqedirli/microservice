package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.CustomerDetailsDto;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(String mobileNumber);

}
