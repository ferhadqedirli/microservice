package com.eazybytes.accounts.mapper;

import com.eazybytes.accounts.dto.AccountDto;
import com.eazybytes.accounts.entity.Account;

public class AccountMapper {

    public static AccountDto mapToAccountDto(Account account) {
        return new AccountDto(
                account.getAccountNumber(),
                account.getAccountType(),
                account.getBranchAddress()
                );
    }

    public static Account mapToAccount(AccountDto accountDto, Account account) {
        account.setAccountNumber(accountDto.accountNumber());
        account.setAccountType(accountDto.AccountType());
        account.setBranchAddress(accountDto.branchAddress());
        return account;
    }

}
