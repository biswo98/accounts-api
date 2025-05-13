package com.accounts.api.service.impl;

import com.accounts.api.dto.AccountDto;
import com.accounts.api.dto.CustomerDto;
import com.accounts.api.entity.Account;
import com.accounts.api.entity.Customer;
import com.accounts.api.exception.types.CustomerAlreadyExistException;
import com.accounts.api.exception.types.ResourceNotFoundException;
import com.accounts.api.mapper.AccountMapper;
import com.accounts.api.mapper.CustomerMapper;
import com.accounts.api.repository.AccountsRepository;
import com.accounts.api.repository.CustomerRepository;
import com.accounts.api.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final CustomerRepository customerRepository;

    private final AccountsRepository accountRepository;


    /**
     * @param customerDto - Customer Object
     */
    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.toCustomer(customerDto);
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistException("Customer already registered with given mobileNumber " +
                    customerDto.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(AccountMapper.createNewAccount(savedCustomer));
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer);
        customerDto.setAccountDto(AccountMapper.mapToAccountDto(account));
        return customerDto;
    }

    /**
     * @param customerDto - Customer Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isCustomerUpdated = false;
        AccountDto accountDto = customerDto.getAccountDto();
        if (accountDto != null) {
            Account account = accountRepository.findById(accountDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "accountNumber", accountDto.getAccountNumber().toString()));

            AccountMapper.mapToAccount(accountDto, account);
            accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "customerId", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto, customer);
            customerRepository.save(customer);
            isCustomerUpdated = true;

        }
        return isCustomerUpdated;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Long customerId = customer.getCustomerId();
        accountRepository.deleteByCustomerId(customerId);
        customerRepository.deleteById(customerId);
        return true;
    }
}
