package com.jie.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.jie.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Service("TokenService")
public class TokenService {

    public String getToken(User user)
    {
        Date start = new Date();
        long currentTime = System.currentTimeMillis() + 60 * 60 * 10000;
        Date end = new Date(currentTime);
        String token = JWT.create().withAudience(String.valueOf(user.getId())).withIssuedAt(start).withExpiresAt(end).sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }

    public String getTokenUserId()
    {
        String token = getRequest().getHeader("token");
        String userId = JWT.decode(token).getAudience().get(0);
        return userId;
    }

    public HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes == null ? null : requestAttributes.getRequest();
    }

}
