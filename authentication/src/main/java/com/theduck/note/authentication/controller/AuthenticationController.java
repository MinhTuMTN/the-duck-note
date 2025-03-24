package com.theduck.note.authentication.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theduck.note.authentication.dto.AuthenticationRequest;
import com.theduck.note.authentication.dto.SignupRequest;
import com.theduck.note.authentication.service.AccountService;
import com.theduck.note.commons.dto.GeneralResponse;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

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
