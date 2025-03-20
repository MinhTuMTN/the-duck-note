package com.theduck.note.gateway.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequest {

    private String email;

    private String password;
}
