package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.AccountContactInfoDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import org.springframework.http.ResponseEntity;

public interface AccountService {

    ResponseEntity<ResponseDto> createAccount(CustomerDto customerDto);

    ResponseEntity<CustomerDto> fetchAccount(String mobileNumber);

    ResponseEntity<ResponseDto> updateAccount(CustomerDto customerDto);

    ResponseEntity<ResponseDto> deleteAccount(String mobileNumber);

    ResponseEntity<String> getBuildInfo();

    ResponseEntity<String> getJavaVersion();

    ResponseEntity<AccountContactInfoDto> getContactInfo();

}
