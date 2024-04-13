package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.AccountDto;
import com.eazybytes.accounts.dto.CustomerDto;
import com.eazybytes.accounts.entity.Account;
import com.eazybytes.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, Account account) {
        return new CustomerDto(
                customer.getName(),
                customer.getEmail(),
                customer.getMobileNumber(),
                new AccountDto(
                        account.getAccountNumber(),
                        account.getAccountType(),
                        account.getBranchAddress()
                )
        );
    }

    public static Customer mapToCustomer(CustomerDto customerDto, Customer customer) {
        customer.setEmail(customerDto.email());
        customer.setMobileNumber(customerDto.mobileNumber());
        customer.setName(customerDto.name());
        return customer;
    }

}
