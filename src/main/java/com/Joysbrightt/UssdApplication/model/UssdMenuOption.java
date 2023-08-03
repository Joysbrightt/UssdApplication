package com.Joysbrightt.UssdApplication.model;

import com.Joysbrightt.UssdApplication.enums.UssdMenuOptionEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data

public class UssdMenuOption {
        private String type;

        private String response;

        @JsonProperty("next_menu_level")
        private String nextMenuLevel;

        private UssdMenuOptionEnum action;

    }


