//package com.Joysbrightt.UssdApplication.config;
//
//import lombok.Value;
//import org.springframework.cache.annotation.AbstractCachingConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
//
//@Configuration
//    @EnableRedisRepositories
//    public class UssdRedisConfiguration extends AbstractCachingConfiguration {
//
//        @Value("${decoded.cache.host}")
//        private String host;
//
//        @Value("${decoded.cache.port}")
//        private int port;
//
//        @Value("${decoded.cache.password}")
//        private String password;
//
//        @Value("${decoded.cache.dfault-ttl}")
//        private String defaultTTL;
//
//        @Bean
//        public LettuceMetricsAutoConfiguration redisConnectionFactory(){
//            RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
//            redisStandaloneConfiguration.setHostName(host);
//            redisStandaloneConfiguration.setHostName(port);
//            redisStandaloneConfiguration.setHostName(password);
//            return  new LettuceMetricsAutoConfiguration(redisStandaloneConfiguration);
//
//        }
//
//
//
//    }
