package com.theduck.note.commons.model;

import java.util.Date;

import com.theduck.note.commons.constant.Gender;
import com.theduck.note.commons.dto.SignupRequest;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProfileModel {
    
    private Long profileId;

    private Long accountId;

    private String email;

    private String fullName;

    private Gender gender;

    private Date dateOfBirth;

    private String address;

    public static ProfileModel fromSignupRequest(SignupRequest request, Long accountId) {
        return ProfileModel.builder()
            .accountId(accountId)
            .fullName(request.getFullName())
            .email(request.getEmail())
            .gender(request.getGender())
            .dateOfBirth(request.getDateOfBirth())
            .address(request.getAddress())
            .build();
    }
}
