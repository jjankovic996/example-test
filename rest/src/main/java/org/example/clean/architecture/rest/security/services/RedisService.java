package org.example.clean.architecture.rest.security.services;


import org.example.clean.architecture.rest.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService {

    private final RedisTemplate template;

    @Autowired
    public RedisService(RedisTemplate template){
        this.template = template;
    }

    public void blacklistToken(String token){
        template.opsForValue().set(token, true);
        template.expire(token, 20, TimeUnit.MINUTES);
    }


    public Boolean isTokenBlackListed(String token){
        return (Boolean) template.opsForValue().get(token);
    }
}
