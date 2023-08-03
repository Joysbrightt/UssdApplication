package com.Joysbrightt.UssdApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UssdMenuOption {
        private String type;

        private String response;

        @JsonProperty("next_menu_level")
        private String nextMenuLevel;

        private UssdMenuOption action;

    }


