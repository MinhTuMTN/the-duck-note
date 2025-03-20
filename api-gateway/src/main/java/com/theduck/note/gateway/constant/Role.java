package com.theduck.note.gateway.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    USER("User"), ADMIN("Admin");

    private String value;
}
