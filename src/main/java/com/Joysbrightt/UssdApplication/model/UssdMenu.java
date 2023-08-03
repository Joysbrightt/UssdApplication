package com.Joysbrightt.UssdApplication.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class UssdMenu {

        @Id
        @JsonProperty("id")
        private String id;

        @JsonProperty("menu_level")
        private String menuLevel;

        @JsonProperty("id")
        private String text;

        @JsonProperty("menu_options")
        private List<UssdMenuOption> menuOptions;

        public String previousMenuLevel;


        @JsonProperty("max_selections")
        private Integer maxSelections;
        public String getPreviousMenuLevel(){
                return previousMenuLevel;
        }

        public void setPreviousMenuLevel(String previousMenuLevel){
                this. menuLevel = menuLevel;
        }
    }
