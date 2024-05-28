package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.constant.AccountConstant;
import com.eazybytes.accounts.dto.AccountContactInfoDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.dto.ResponseDto;
import com.eazybytes.accounts.entity.Account;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.CustomerAlreadyExistsException;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final Environment environment;
    private final AccountContactInfoDto accountContactInfoDto;

    @Value("${build.version}")
    private String buildVersion;

    /**
     * @param customerDto CustomerDto Object
     * @return ResponseDto
     * This method creates an account
     */
    @Override
    public ResponseEntity<ResponseDto> createAccount(CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.mobileNumber());
        if (optionalCustomer.isPresent())
            throw new CustomerAlreadyExistsException(
                    String.format("Customer already registered with given mobileNumber: %s", customerDto.mobileNumber())
            );
        var customer = new Customer();
        CustomerMapper.mapToCustomer(customerDto, customer);
        customer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(customer));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountConstant.STATUS_201, AccountConstant.MESSAGE_201));
    }

    /**
     * @param mobileNumber - Input mobile number
     * @return ResponseDto
     * This method fetches Account by customer's mobile number
     */
    @Override
    public ResponseEntity<CustomerDto> fetchAccount(String mobileNumber) {
        var customer = customerRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

        var account = accountRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
                );
        var customerDto = CustomerMapper.mapToCustomerDto(customer, account);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    /**
     * @param customerDto - Input customerDto
     * @return ResponseDto
     * This method updates Account
     */
    @Override
    public ResponseEntity<ResponseDto> updateAccount(CustomerDto customerDto) {
        var accountDto = customerDto.account();
        if (!isNull(accountDto)) {
            var account = accountRepository.findById(accountDto.accountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountDto.accountNumber().toString())
            );
            AccountMapper.mapToAccount(accountDto, account);
            account = accountRepository.save(account);

            var customerId = account.getCustomerId();
            var customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
    }

    /**
     * @param mobileNumber - Input mobile number
     * @return ResponseDto
     * This method deletes Account by given mobile number
     */
    @Override
    public ResponseEntity<ResponseDto> deleteAccount(String mobileNumber) {
        var customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(AccountConstant.STATUS_200, AccountConstant.MESSAGE_200));
    }

    /**
     * @return String
     * This method returns build version of application
     */
    @Override
    public ResponseEntity<String> getBuildInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(buildVersion);
    }

    @Override
    public ResponseEntity<String> getJavaVersion() {
        return ResponseEntity.status(HttpStatus.OK).body(environment.getProperty("JAVA_HOME"));
    }

    @Override
    public ResponseEntity<AccountContactInfoDto> getContactInfo() {
        return ResponseEntity.status(HttpStatus.OK).body(accountContactInfoDto);
    }

    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());
        long randomNumber = 1000000000L + new Random().nextInt(900000000);

        account.setAccountNumber(randomNumber);
        account.setAccountType(AccountConstant.SAVINGS);
        account.setBranchAddress(AccountConstant.ADDRESS);
        return account;
    }

}
