package com.theduck.note.commons.dto;

import java.util.Date;

import com.theduck.note.commons.constant.Gender;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequest {

    private String email;

    private String fullName;

    private String password;

    private String confirmPassword;

    private Gender gender;

    private Date dateOfBirth;

    private String address;
}
