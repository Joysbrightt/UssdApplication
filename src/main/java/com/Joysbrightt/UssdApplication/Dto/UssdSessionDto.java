package com.Joysbrightt.UssdApplication.Dto;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Data
@Getter
@Setter
@Component
@Builder
@NoArgsConstructor
public class UssdSessionDto {
    @Id
    private String id;

    private String text;

    private String phoneNumber;

    private String currentMenuLevel;

    private String previousMenuLevel;

    private String serviceCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCurrentMenuLevel() {
        return currentMenuLevel;
    }

    public void setCurrentMenuLevel(String currentMenuLevel) {
        this.currentMenuLevel = currentMenuLevel;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }
}
