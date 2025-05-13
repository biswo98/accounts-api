package com.accounts.api.mapper;

import com.accounts.api.constant.AccountsConstants;
import com.accounts.api.dto.AccountDto;
import com.accounts.api.entity.Account;
import com.accounts.api.entity.Customer;

import java.util.Random;

public class AccountMapper {

    /**
     * @param customer - Customer Object
     * @return the new account details
     */
    public static Account createNewAccount(Customer customer) {
        Account newAccount = new Account();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.SAVINGS);
        newAccount.setBranchAddress(AccountsConstants.ADDRESS);
        return newAccount;
    }

    public static AccountDto mapToAccountDto(Account account) {
        return AccountDto.builder()
                .accountNumber(account.getAccountNumber())
                .accountType(account.getAccountType())
                .branchAddress(account.getBranchAddress())
                .build();
    }

    public static void mapToAccount(AccountDto accountDto, Account account) {
       account.setAccountType(accountDto.getAccountType());
       account.setBranchAddress(accountDto.getBranchAddress());
       account.setAccountNumber(accountDto.getAccountNumber());

    }


}
