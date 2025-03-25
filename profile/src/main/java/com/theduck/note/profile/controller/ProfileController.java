package com.theduck.note.profile.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theduck.note.commons.dto.ApiResponse;
import com.theduck.note.commons.model.ProfileModel;
import com.theduck.note.profile.service.ProfileService;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor
@RequestMapping("/api/profiles")
public class ProfileController {

    private ProfileService profileService;

    @PostMapping
    public ResponseEntity<?> createProfile(@RequestBody ProfileModel profile) {
        return ResponseEntity.ok(
            ApiResponse.builder()
                .code(HttpStatus.OK.value())
                .message("Create profile successfully")
                .result(this.profileService.createProfile(profile))
                .build()
        );
    }
}
