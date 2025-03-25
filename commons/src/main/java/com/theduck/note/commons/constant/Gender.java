package com.theduck.note.commons.constant;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("male"), FEMALE("female"), OTHER("other");

    private String value;

    public static Gender of(String gender) {
        return Arrays.stream(Gender.values())
                .filter(g -> g.getValue().equals(gender))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Gender not found"));
        
    }
}
