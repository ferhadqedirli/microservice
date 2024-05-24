package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.CustomerService;
import com.eazybytes.accounts.service.client.CardsFeignClient;
import com.eazybytes.accounts.service.client.LoansFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(String mobileNumber) {
        var customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        var account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
                );

        var loanDto = loansFeignClient.fetchLoanDetails(mobileNumber);
        var cardDto = cardsFeignClient.fetchCardDetails(mobileNumber);

        var customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(
                customer,
                account,
                cardDto.getBody(),
                loanDto.getBody()
        );

        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }

}
