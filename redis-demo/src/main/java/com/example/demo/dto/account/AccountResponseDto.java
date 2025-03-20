package com.example.demo.dto.account;

import java.io.Serializable;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.entity.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto implements Serializable {
    
    private int accountId;

    private String userName;

    private Date dateOfBirth;

    private Date createdAt;

    private Date updatedAt;

    public static AccountResponseDto fromEntity(Account account) {
        return AccountResponseDto.builder()
            .accountId(account.getAccountId())
            .userName(account.getUserName())
            .dateOfBirth(account.getDateOfBirth())
            .createdAt(account.getCreatedAt())
            .updatedAt(account.getUpdatedAt())
            .build();
    }

    public ResponseEntity<AccountResponseDto> toResponseEntity() {
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(this);
    }
}
