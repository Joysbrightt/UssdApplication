package com.Joysbrightt.UssdApplication.data;

import com.Joysbrightt.UssdApplication.model.UssdMenu;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UssdRepository extends MongoRepository<UssdMenu, String> {
    UssdMenu findByCode(String code);
    UssdMenu existById(String id);
    void delete(String id);
//    UssdMenu delete(String id);

//    private void save()

    }
