package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.GeneralResponse;
import com.example.demo.dto.account.AccountRequestDto;
import com.example.demo.service.AccountService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/accounts")
public class AccountController {

    private AccountService accountService;

    @GetMapping
    public ResponseEntity<?> getAllAccounts(
            @RequestParam(defaultValue = "false") boolean forceCache) {
        long start = System.currentTimeMillis();
        var result = this.accountService.getAllAccounts(forceCache);
        long end = System.currentTimeMillis();
        log.info("Execution time: " + (end - start) + "ms");
        return GeneralResponse.builder()
                .message("Success")
                .data(result)
                .build()
                .toResponseEntity();
    }

    @PostMapping
    public ResponseEntity<?> createAccount(
            @RequestBody AccountRequestDto request) {
        return GeneralResponse.builder()
                .message("Success")
                .data(this.accountService.createAccount(request))
                .build()
                .toResponseEntity();
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountByAccountId(@PathVariable int accountId) {
        return GeneralResponse.builder()
                .message("Success")
                .data(this.accountService.getAccountByAccountId(accountId))
                .build()
                .toResponseEntity();
    }
}
