package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    
    List<Account> findAccountsByDeletedIsFalse();

    Optional<Account> findAccountByAccountIdAndDeletedIsFalse(int accountId);
}
