package com.theduck.note.authentication.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.theduck.note.authentication.dto.AuthenticationRequest;
import com.theduck.note.authentication.dto.AuthenticationResponse;
import com.theduck.note.authentication.entity.AccountEntity;
import com.theduck.note.authentication.feign.ProfileFeignClient;
import com.theduck.note.authentication.repository.AccountRepository;
import com.theduck.note.commons.constant.Role;
import com.theduck.note.commons.dto.SignupRequest;
import com.theduck.note.commons.model.ProfileModel;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    private PasswordEncoder passwordEncoder;

    private JwtTokenService tokenService;

    private ProfileFeignClient profileClient;

    @Transactional
    public AuthenticationResponse signupNewAccount(SignupRequest request) throws Exception {
        AccountEntity account = new AccountEntity();
        account.setEmail(request.getEmail());
        account.setPassword(this.passwordEncoder.encode(request.getPassword()));
        account.setRole(Role.USER.getValue());

        AccountEntity result = this.accountRepository.save(account);
        ProfileModel profileModel = ProfileModel.fromSignupRequest(request, result.getId());
        this.profileClient.createProfile(profileModel);

        String token = this.tokenService.generateToken(account);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse signinAccount(AuthenticationRequest request) throws Exception {
        AccountEntity accountEntity = this.accountRepository
            .findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!this.passwordEncoder.matches(request.getPassword(), accountEntity.getPassword())) {
            throw new Exception("Invalid password");
        }

        String token = this.tokenService.generateToken(accountEntity);
        return new AuthenticationResponse(token);
    }

    // private void sendEmailNotification(SignupRequest request) {
    // EmailNoticationEvent event =
    // EmailNoticationEvent.builder().email(request.getEmail())
    // .fullName(request.getFullName()).build();
    // this.rabbitTemplate.convertAndSend(EMAIL_NOTIFICATION, event);
    // }
}
