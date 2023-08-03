package com.Joysbrightt.UssdApplication.Dto;

import com.Joysbrightt.UssdApplication.dto.UssdSessionDto;
import com.Joysbrightt.UssdApplication.model.UssdMenu;
import org.springframework.stereotype.Component;

@Component
public class UssdConverter {
    public UssdMenu converterToUssdMenu(UssdSessionDto ussdSessionDto){
        UssdMenu ussdMenu = new UssdMenu();
        ussdMenu.setId(ussdMenu.getId());

        return ussdMenu;
    }

}
