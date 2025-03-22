package com.theduck.note.commons.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("male"), FEMALE("female"), OTHER("other");

    private String value;
}
