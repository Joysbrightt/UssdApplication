package com.Joysbrightt.UssdApplication.Dto;

import com.Joysbrightt.UssdApplication.model.UssdMenu;
import org.springframework.stereotype.Component;

@Component
public class UssdConverter {
    public UssdMenu converterToUssdMenu(UssdSessionDto ussdSessionDto){
        UssdMenu ussdMenu = new UssdMenu();
        ussdMenu.setId(ussdMenu.getId());
        ussdMenu.setText(ussdSessionDto.getText());

        return ussdMenu;
    }


    public UssdSessionDto convertToUssdSessionDto(UssdMenu ussdMenu){
        UssdSessionDto sessionDto = new UssdSessionDto();
        sessionDto.setId(ussdMenu.getId());
        sessionDto.setText(ussdMenu.getText());
        return sessionDto;
    }

}
