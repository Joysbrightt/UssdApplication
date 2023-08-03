package com.Joysbrightt.UssdApplication.controller;

import com.Joysbrightt.UssdApplication.service.UssdMenuRoutingService;
import com.Joysbrightt.UssdApplication.service.UssdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class UssdController {

    @Autowired
    private UssdService ussdService;
    @Autowired
    private UssdMenuRoutingService routingService;

    @RequestMapping("/ussd")
    public String ussdRequest(@RequestParam("code") String code) {
        return ussdService.getMenu(code);
    }

    @RequestMapping("/ussd")
    public String ussdServiceRequest(@RequestParam("code") String serviceCode){
        ussdService.getMenu(serviceCode);
        return String.valueOf(new ResponseEntity<>(serviceCode, HttpStatus.ACCEPTED));
    }

    @PostMapping(path = "ussd")
    public String ussdIngress(@RequestParam String sessionId, @RequestParam String serviceCode,@RequestParam String phoneNumber, @RequestParam String text) throws IOException {
        try{
            return routingService.menulevelRouter(sessionId, serviceCode, phoneNumber, text);
        }catch (IOException exception){
            return "END Service is temporarily down";
        }
    }

    @GetMapping(path = "getmenu")
    public ResponseEntity getMenu(@RequestParam String menu){
        try{
            return new ResponseEntity<>(routingService.getMenu(menu), );
        } catch (IOException exception){
            return new ResponseEntity<>(routingService.getMenu(menu), 401);
        }
    }
}

