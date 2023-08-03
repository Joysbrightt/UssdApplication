package com.Joysbrightt.UssdApplication.service;

import com.Joysbrightt.UssdApplication.Dto.UssdConverter;
import com.Joysbrightt.UssdApplication.data.UssdRepository;
import com.Joysbrightt.UssdApplication.Dto.UssdSessionDto;
import com.Joysbrightt.UssdApplication.enums.UssdMenuOptionEnum;
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
    private UssdMenuOption menuOption;

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
            return processMenuOptionResponse(menuOption); // Call the method to process response type
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

        if (menuOption.getAction() == UssdMenuOptionEnum.PROCESS_ACC_BALANCE) {
            variableMap.put("account_balance", "10000");
            response = replaceVariable(variableMap, response);
        } else if (menuOption.getAction() == UssdMenuOptionEnum.PROCESS_ACC_NUMBER) {
            variableMap.put("account_number", "12345567712"); // Corrected "acccount_number" to "account_number"
            response = replaceVariable(variableMap, response);
        } else if (menuOption.getAction() == UssdMenuOptionEnum.PROCESS_ACC_PHONE_NUMBER) {
            variableMap.put("phone_number", "12345678912");
            response = replaceVariable(variableMap, response); // Changed "variableMap.replace" to "replaceVariable"
        }
        return response;
    }


    public String replaceVariable(Map<String, String> variablesMap, String response) {
       String formattedResponse = response;
       for (Map.Entry<String, String> entry : variablesMap.entrySet()) {
       String key = entry.getKey();
       String value = entry.getValue();
       formattedResponse = formattedResponse.replace("{ " + key + " }", value);
       }
       return formattedResponse;
    }

    public UssdSessionDto updateUssdSessionMenuLevel(UssdSessionDto sessionDto, String menulevel) {

        UssdMenu ussdMenu = ussdConverter.converterToUssdMenu(sessionDto);
        ussdMenu.setPreviousMenuLevel(ussdMenu.getMenuLevel());
        ussdMenu.setMenuLevel(menulevel);
        UssdMenu updatedMenu = ussdRepository.save(ussdMenu);
        return ussdConverter.convertToUssdSessionDto(updatedMenu);

//                UssdSessionDto updatedSessionDto = UssdSessionDto.builder()
//                    .id(sessionDto.getId())
//                    .text(sessionDto.getText())
//                    .phoneNumber(sessionDto.getPhoneNumber())
//                    .currentMenuLevel(menulevel)
//                    .serviceCode(sessionDto.getServiceCode())
//                    .build();
//        return sessionService.update(updatedSessionDto);
    }

    public UssdMenu checkAndSetSession(String sessionId, String serviceCode, String phoneNumber, String text) {

        UssdSessionDto sessionDto = sessionService.get(sessionId);

        if (sessionDto != null) {
            sessionDto.setText(text);
            return sessionService.update(sessionDto);
        } else{
            sessionDto = new UssdSessionDto();
            sessionDto.setCurrentMenuLevel("1");
            sessionDto.setPreviousMenuLevel("1");
            sessionDto.setId(sessionId);
            sessionDto.setPhoneNumber(phoneNumber);
            sessionDto.setServiceCode(serviceCode);
            sessionDto.setText(text);
        }

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

}

