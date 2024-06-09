package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.CardDto;
import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.dto.LoanDto;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.CustomerService;
import com.eazybytes.accounts.service.client.CardsFeignClient;
import com.eazybytes.accounts.service.client.LoansFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;
    @Qualifier("cards") private final CardsFeignClient cardsFeignClient;
    @Qualifier("loans") private final LoansFeignClient loansFeignClient;

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    public CustomerServiceImpl(CustomerRepository customerRepository, AccountRepository accountRepository, CardsFeignClient cardsFeignClient, LoansFeignClient loansFeignClient) {
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
        this.cardsFeignClient = cardsFeignClient;
        this.loansFeignClient = loansFeignClient;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Customer Details based on a given mobileNumber
     */
    @Override
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(String mobileNumber, String correlationId) {
        logger.debug("eazyBank-correlation-id found: {} ", correlationId);

        var customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        var account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
                );

        LoanDto loanDtoBody = null;
        CardDto cardDtoBody = null;

        var loanDto = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        if (loanDto != null) {
            loanDtoBody = loanDto.getBody();
        }
        var cardDto = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        if (cardDto != null) {
            cardDtoBody = cardDto.getBody();
        }

        var customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(
                customer,
                account,
                cardDtoBody,
                loanDtoBody
        );

        return ResponseEntity.status(HttpStatus.OK).body(customerDetailsDto);
    }

}
