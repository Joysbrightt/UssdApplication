package com.Joysbrightt.UssdApplication.Dto;

import lombok.Data;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@CacheConfig
@RedisHash(value="session", timeToLive=180)
public class UssdSession implements Serializable {

    long serialVersionUID = 1L;

    @Id
    private String id;

    private String sessionId;

    private String serviceCode;

    private String phoneNumber;
    private String text;
    private String previousMenuLevel;
    private String currentMenuLevel;
}
