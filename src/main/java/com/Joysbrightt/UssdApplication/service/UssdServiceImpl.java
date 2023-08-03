package com.Joysbrightt.UssdApplication.service;

import com.Joysbrightt.UssdApplication.data.UssdRepository;
import com.Joysbrightt.UssdApplication.model.UssdMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UssdServiceImpl implements UssdService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private UssdRepository ussdMenuRepository;


    // Simulate a database or external API call
    @Cacheable("ussdMenuCache")
    public String getBasicMenu(String code) {
        // Replace this with your actual logic to fetch the menu based on the USSD code
        String menu = "Welcome to My USSD Service!\n";
        menu += "1. Check Balance\n";
        menu += "2. Transfer Money\n";
        menu += "3. Recharge\n";
        menu += "4. Exit";
        return menu;
    }

        @Cacheable("ussdMenuCache")
        public String getMenu(String serviceCode) {
            UssdMenu ussdMenu = ussdMenuRepository.findByCode(serviceCode);
            if (ussdMenu != null) {
                return ussdMenu.getMenuLevel();
            } else {
                return "Invalid USSD code. Please try again.";
            }
        }

        public Map<String, UssdMenu> loadMenu(){
        List<UssdMenu> allMenus = ussdMenuRepository.findAll();
            Map<String, UssdMenu> menuMap = new HashMap<>();
            for (UssdMenu ussdMenu : allMenus){
                menuMap.put(ussdMenu.getMenuLevel(), ussdMenu);
            }
            return menuMap;
        }
    }


