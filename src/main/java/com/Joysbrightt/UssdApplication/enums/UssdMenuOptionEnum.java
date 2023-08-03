package com.Joysbrightt.UssdApplication.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter

public enum UssdMenuOptionEnum {
    PROCESS_ACC_BALANCE("PROCESS_ACC_BALANCE"),
    PROCESS_ACC_PHONE_NUMBER("PROCESS_ACC_PHONE_NUMBER"),
    PROCESS_ACC_NUMBER("PROCESS_ACC_NUMBER");

    private String action;

    UssdMenuOptionEnum(String action) {
        this.action = action;
    }

    @JsonValue
    private String getAction() {
        return action;
    }
}



