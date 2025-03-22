package com.theduck.note.commons.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AccountStatus {
    TEMPORARY("temporary"), ACTIVE("active"), INACTIVE("inactive");

    private String value;
}
