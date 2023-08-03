package com.Joysbrightt.UssdApplication.service;

import com.Joysbrightt.UssdApplication.Dto.UssdConverter;
import com.Joysbrightt.UssdApplication.data.UssdRepository;
import com.Joysbrightt.UssdApplication.dto.UssdSessionDto;
import com.Joysbrightt.UssdApplication.model.UssdMenu;
import com.Joysbrightt.UssdApplication.model.UssdMenuOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@Service
public class UssdMenuRoutingService {
    @Autowired
    private UssdConverter ussdConverter;
    @Autowired
    private UssdService menuService;

    @Autowired
    UssdSessionService sessionService;
    @Autowired
    private UssdRepository ussdRepository;

    public String menuLevelRouter(String sessionId, String serviceCode, String phoneNumber, String text) throws IOException {
        Map<String, UssdMenu> menus = menuService.loadMenu();
        UssdSessionDto sessionDto = checkAndSetSession(sessionId, serviceCode, phoneNumber, text);

        if (text.length() > 0) {
            return getNextUssdMenuItem(sessionDto, menus);
        } else {
            return menus.get(sessionDto.getCurrentMenuLevel()).getText();
        }

    }

    public String getNextUssdMenuItem(UssdSessionDto sessionDto, Map<String, UssdMenu> menus) throws IOException {
        String[] levels = sessionDto.getText().split("\\*");
        String lastValue = levels[levels.length - 1];
        UssdMenu menuLevel = menus.get(sessionDto.getCurrentMenuLevel());

        if (Integer.parseInt(lastValue) <= menuLevel.getMaxSelections()) {
            UssdMenuOption menuOption = menuLevel.getMenuOptions().get(Integer.parseInt(lastValue) - 1);
            return processMenuOption(sessionDto, menuOption);
        }
        return "CON";
    }

    public String getMenu(String menuLevel) throws IOException {
        Map<String, UssdMenu> menus = menuService.loadMenu();
        return menus.get(menuLevel).getText();
    }

    public String processMenuOption(UssdSessionDto sessionDto, UssdMenuOption menuOption) throws IOException {

        if (menuOption.getType().equals("response")) {
            return processMenuOption(menuOption);
        } else if (menuOption.getType().equals("level")) {
            updateUssdSessionMenuLevel(sessionDto, menuOption.getNextMenuLevel());
            return getMenu(menuOption.getNextMenuLevel());
        } else {
            return "CON";
        }

    }

    public String processMenuOptionResponse(UssdMenuOption menuOption) {
        String response = menuOption.getResponse();
        Map<String, String> variableMap = new HashMap<>();

        if (menuOption.getAction() == UssdMenuOption.PROCESS_ACC_BALANCE) {
            variableMap.put("account_balance", "10000");
            response = replaceVariable(variableMap, response);
        } else if (menuOption.getAction() == UssdMenuOptionAction.PROCESS_ACC_NUMBER) {
            variableMap.put("acccount_number", "12345567712");
            response = replaceVariable(variableMap, response);
        } else if (menuOption.getAction() == UssdMenuOptionAction.PROCESS_ACC_PHONE_NUMBER) {
            variableMap.put("phone_number", "12345678912");
            response = variableMap.replace(variableMap.toString(), response);
        }
        return response;
    }

    public String replaceVariable(Map<String, String> variablesMap, String response) {
        StringBuilder sub = new StringBuilder((CharSequence) variablesMap);
//        StringSubtitutor sub = new StringSubstitutor(variablesMap);
        return sub.(response);
    }

    public UssdSessionDto updateUssdSessionMenuLevel(UssdSessionDto sessionDto, String menulevel) {
        sessionDto.setPreviousMenuLevel(sessionDto.getCurrentMenuLevel());
        sessionDto.setCurrentMenuLevel(menulevel);
        return sessionService.update(sessionDto);
    }

    public UssdSessionDto checkAndSetSession(String sessionId, String serviceCode, String phoneNumber, String text) {

        UssdSessionDto sessionDto = sessionService.get(sessionId);

        if (sessionDto != null) {
            sessionDto.setText(text);
            return sessionService.update(sessionDto);
        }
//        sessionDto = UssdSessionDto.builder().currentMenuLevel(sessionDto.getCurrentMenuLevel())
//                        .phoneNumber(sessionDto.getPhoneNumber())
//                        .text(sessionDto.getText())
//                        .serviceCode(sessionDto.setServiceCode(serviceCode))
//                        .build();

        sessionDto.setCurrentMenserviceCodeuLevel("1");
        sessionDto.setPreviousMenuLevel("1");
        sessionDto.setId(sessionId);
        sessionDto.setPhoneNumber(phoneNumber);
        sessionDto.setServiceCode(serviceCode);
        sessionDto.setText(text);
        return sessionService.createUssdSession(sessionDto);
    }

//        Updating the ussd application

//      @GetMapping(path = "menus")
//    public ResponseEntity<?> menusLoad() throws IOException {
//        Map<String, Menu> response = menuService.loadMenus();
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @PostMapping(path = "ussd")
//    public ResponseEntity<?> ussdIngress(@RequestParam String sessionId, @RequestParam String serviceCode,
//                              @RequestParam String phoneNumber, @RequestParam String text) throws IOException {
//        String response = ussdRoutingService.menuLevelRouter(sessionId, serviceCode, phoneNumber, text);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//}

    git remote add origin https://github.com/Joysbrightt/a.git
    git branch -M main
    git push -u origin main

}

