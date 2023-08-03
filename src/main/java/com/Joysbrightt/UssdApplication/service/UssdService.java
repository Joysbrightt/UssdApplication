package com.Joysbrightt.UssdApplication.service;

import com.Joysbrightt.UssdApplication.model.UssdMenu;

import java.util.Map;

public interface UssdService {

    public String getBasicMenu(String code);

    public String getMenu(String code);


    Map<String, UssdMenu> loadMenu();

}
