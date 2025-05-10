package com.accounts.api.service.impl;

import com.accounts.api.dto.Customer;
import com.accounts.api.service.AccountService;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    /**
     * @param customer - Customer Object
     */
    @Override
    public void createAccount(Customer customer) {

    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    @Override
    public Customer fetchAccount(String mobileNumber) {
        return null;
    }

    /**
     * @param customer - Customer Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(Customer customer) {
        return false;
    }

    /**
     * @param mobileNumber - Input Mobile Number
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    public boolean deleteAccount(String mobileNumber) {
        return false;
    }
}
