package com.Joysbrightt.UssdApplication.service;

import com.Joysbrightt.UssdApplication.Dto.UssdConverter;
import com.Joysbrightt.UssdApplication.data.UssdRepository;
import com.Joysbrightt.UssdApplication.Dto.UssdSessionDto;
import com.Joysbrightt.UssdApplication.model.UssdMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
    @Service
    public class UssdSessionService {
        @Autowired
        private UssdRepository ussdSessionRepository;
        @Autowired
        UssdMenuRoutingService menuRoutingService;

        @Autowired
        private UssdConverter ussdConverter;
        @Autowired
       private UssdSessionDto ussdSessionDto;

        public UssdSessionDto get(String id){
            UssdMenu ussdMenu = ussdSessionRepository.findById(id).orElse(null);
            if (ussdMenu != null) {
                return ussdConverter.convertToUssdSessionDto(ussdMenu);
            }
            return null;

        }

        public UssdMenu update(UssdMenu ussdMenu){
            if(ussdSessionRepository.existsById(ussdMenu.getId())){
//                UssdMenu ussdMenu = ussdConverter.converterToUssdMenu(ussdSessionDto);
                return ussdSessionRepository.save(ussdMenu);
            }
            else{
                throw new IllegalArgumentException("A session must have an ud to be updated");
            }

        }

//        public UssdSessionDto update(UssdSessionDto ussdSessionDto) {
//            if (ussdSessionRepository.existsById(ussdSessionDto.getId())) {
//                return ussdSessionRepository.save(ussdSessionDto);
////                Inferred type 'S' for type parameter 'S' is not within its bound; should extend 'com.Joysbrightt.UssdApplication.model.UssdMenu'
//            }
//            throw new IllegalArgumentException("A session must have an id to be updated");
//        }
            public void delete(String id){
                ussdSessionRepository.deleteById(ussdSessionDto.getId());
            }

        public static void main(String[] args) {
            String name = "Tomisin";
            System.out.println(name.toString());
        }

        public UssdSessionDto createUssdSession(UssdSessionDto sessionDto) {
            return sessionDto;
        }
    }

