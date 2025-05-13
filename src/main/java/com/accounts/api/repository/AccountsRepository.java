package com.accounts.api.repository;

import com.accounts.api.entity.Account;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountsRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByCustomerId(Long accountNumber);

    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
