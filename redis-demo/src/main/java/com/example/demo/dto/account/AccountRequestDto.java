package com.example.demo.dto.account;

import java.util.Date;

import com.example.demo.entity.Account;

import lombok.Data;

@Data
public class AccountRequestDto {
    
    private String userName;

    private String password;

    private String confirmPassword;

    private Date dateOfBirth;

    public Account toEntity() {
        return Account.builder()
            .userName(this.userName)
            .password(this.password)
            .dateOfBirth(this.dateOfBirth)
            .build();
    }
}
