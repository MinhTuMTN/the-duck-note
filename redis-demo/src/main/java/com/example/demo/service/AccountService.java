package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.demo.dto.account.AccountRequestDto;
import com.example.demo.dto.account.AccountResponseDto;
import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    @CachePut(value = "accounts", key = "#result.accountId")
    @CacheEvict(value = "accounts", allEntries = true)
    public AccountResponseDto createAccount(AccountRequestDto request) {
        Account account = request.toEntity();
        Account result = this.accountRepository.save(account);
        return AccountResponseDto.fromEntity(result);
    }

    @Cacheable(value = "accounts", condition = "#forceCache == false", key = "#root.methodName")
    @CachePut(value = "accounts", condition = "#forceCache", key = "#root.methodName")
    public List<AccountResponseDto> getAllAccounts(boolean forceCache) {
        System.out.println("Getting accounts from database");
        List<Account> accounts = this.accountRepository.findAccountsByDeletedIsFalse();
        return accounts.stream().map(AccountResponseDto::fromEntity).collect(Collectors.toList());
    }

    public List<AccountResponseDto> getAllAccounts() {
        return this.getAllAccounts(false);
    }

    @Cacheable(value = "accounts", key = "#p0")
    public AccountResponseDto getAccountByAccountId(int accountId) {
        System.out.println("Getting account (id=" + accountId + ") from database");
        return this.accountRepository.findAccountByAccountIdAndDeletedIsFalse(accountId)
                .map(AccountResponseDto::fromEntity)
                .orElseThrow(() -> new RuntimeException("Account can't be found"));
    }
}
