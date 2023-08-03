package com.Joysbrightt.UssdApplication.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum UssdMenuOption {
        PROCESS_ACC_BALANCE("PROCESS_ACC_BALANCE"),
        PROCESS_ACC_PHONE_NUMBER("PROCESS_ACC_PHONE_NUMBER"),
        PROCESS_ACC_NUMBER("PROCESS_ACC_NUMBER");

        private String action;
       public void UssdMenuOptionAction(String action){
            this.action = action;
        }

    UssdMenuOption(String processAccBalance) {
    }

    @JsonValue
        private String getAction(){
            return action;
        }
    }


