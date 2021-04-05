package org.example.clean.architecture.rest.security.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    RedisTemplate template;

    public void setValue(String key, String value){

        template.opsForHash().put("USER",key, value);
    }


    public Object getValue(String key){

        return template.opsForHash().get("USER",key);
    }
}
