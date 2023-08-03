package com.Joysbrightt.UssdApplication.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Data
@Getter
@Setter
@Component
@Builder
public class UssdSessionDto {
    @Id
    private String id;

    private String text;

    private int phoneNumber;

    private int currentMenuLevel;

    private int serviceCode;

//    public void setCurrentMenuLevel(String menulevel) {
//
//    }
//
//    public String getCurrentMenuLevel() {
//        return null;
//    }

//    public String getText(String text) {
//        this.text = text;
//        return text;
//
//    }

    public void setPreviousMenuLevel(String currentMenuLevel) {

    }

    public void setText(String text) {

    }

    public void setPhoneNumber(String phoneNumber) {
    }

    public int setServiceCode(String serviceCode) {
        return 0;
    }
}
