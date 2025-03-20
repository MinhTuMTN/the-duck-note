package com.theduck.note.gateway.service;

import static com.theduck.note.commons.constant.MessageQueue.EMAIL_NOTIFICATION;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.theduck.note.commons.event.EmailNoticationEvent;
import com.theduck.note.gateway.constant.Role;
import com.theduck.note.gateway.dto.AuthenticationRequest;
import com.theduck.note.gateway.dto.AuthenticationResponse;
import com.theduck.note.gateway.dto.SignupRequest;
import com.theduck.note.gateway.entity.AccountEntity;
import com.theduck.note.gateway.repository.AccountRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenService jwtTokenService;

    private final RabbitTemplate rabbitTemplate;

    public AuthenticationResponse signupNewAccount(SignupRequest request) throws Exception {
        AccountEntity account = new AccountEntity();
        account.setEmail(request.getEmail());
        account.setPassword(this.passwordEncoder.encode(request.getPassword()));
        account.setRole(Role.USER.getValue());

        this.accountRepository.save(account);
        AuthenticationRequest authenticationRequest = AuthenticationRequest.builder().email(request.getEmail())
                .password(request.getPassword()).build();
        this.sendEmailNotification(request);

        return this.signinAccount(authenticationRequest);
    }

    public AuthenticationResponse signinAccount(AuthenticationRequest request) throws Exception {
        String token = this.jwtTokenService.createJwtToken(request);
        return new AuthenticationResponse(token);
    }

    private void sendEmailNotification(SignupRequest request) {
        EmailNoticationEvent event = EmailNoticationEvent.builder().email(request.getEmail())
                .fullName(request.getFullName()).build();
        this.rabbitTemplate.convertAndSend(EMAIL_NOTIFICATION, event);
    }
}
