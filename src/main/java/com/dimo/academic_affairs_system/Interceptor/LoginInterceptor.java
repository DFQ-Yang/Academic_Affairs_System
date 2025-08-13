package com.dimo.academic_affairs_system.Interceptor;

import com.dimo.academic_affairs_system.pojo.StandardException;
import com.dimo.academic_affairs_system.utils.JwtUtils;
import com.dimo.academic_affairs_system.utils.ThreadLocalUtils;
import io.micrometer.common.util.StringUtils;
import io.netty.util.internal.StringUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Map;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        log.info("preHandle in progress");
        if(token == null){
            throw new StandardException(401, "Jwt not provided, Unauthorized");
        }
        try{
            //Check availability
            Map<String, Object> claims = JwtUtils.parseJWT(token);
        }
        catch (Exception e){
            response.setStatus(401);
            throw new StandardException(401, "Jwt Expired");
        }
        //Check redis
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        String redisToken = operations.get(token);
        if(redisToken == null){
            throw new StandardException(401, "Unavailable jwt, Unauthorized");
        }
        ThreadLocalUtils.set(token);
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtils.remove();
    }
}
