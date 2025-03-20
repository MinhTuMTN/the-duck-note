package com.theduck.note.gateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theduck.note.commons.dto.GeneralResponse;
import com.theduck.note.gateway.dto.AuthenticationRequest;
import com.theduck.note.gateway.dto.SignupRequest;
import com.theduck.note.gateway.service.AccountService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    @PostMapping("signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) throws Exception {
        return ResponseEntity.ok(GeneralResponse.builder().message("Sign up successfully")
                .result(this.accountService.signupNewAccount(request)).build());
    }

    @PostMapping("signin")
    public ResponseEntity<?> signin(@RequestBody AuthenticationRequest request) throws Exception {
        return ResponseEntity.ok(this.accountService.signinAccount(request));
    }
}
