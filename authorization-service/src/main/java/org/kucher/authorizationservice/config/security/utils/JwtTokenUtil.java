package org.kucher.authorizationservice.config.security.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.jsonwebtoken.*;
import org.kucher.authorizationservice.config.exceptions.api.JwtTokenGenerationException;
import org.kucher.authorizationservice.config.utils.mapper.deserializer.LocalDateTimeDeserializer;
import org.kucher.authorizationservice.config.utils.mapper.serializer.LocalDateTimeSerializer;
import org.kucher.authorizationservice.config.security.entity.UserToJwt;
import org.kucher.authorizationservice.service.dto.UserDTO;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class JwtTokenUtil {


    private static final String jwtSecret;
    private static final String jwtIssuer;
    private static final ObjectMapper mapper;

    static {
        jwtSecret = "NDQ1ZjAzNjQtMzViZi00MDRjLTljZjQtNjNjYWIyZTU5ZDYw";
        jwtIssuer = "KuchkovskiiEDN";
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        SimpleModule module = new SimpleModule();
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        mapper.registerModule(module);
    }

    public static String generateAccessToken(UserDTO user) {
        Map<String, Object> map = new HashMap<>();

        UserToJwt userToJwt = new UserToJwt(user.getUuid(),
                user.getDtCreate(),
                user.getDtUpdate(),
                user.getEmail(),
                user.getRole());
        try {
            String json = mapper.writeValueAsString(userToJwt);
            map.put("user", json);
        }catch (JsonProcessingException e) {
            throw new JwtTokenGenerationException();
        }

        return Jwts.builder()
                .setClaims(map)
                .setIssuer(jwtIssuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(7))) // 1 week
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public static UserToJwt getUser(String token) throws JsonProcessingException {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return mapper.readValue(claims.get("user").toString(), UserToJwt.class);
    }

    public static boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            //logger.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            //logger.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            //logger.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            //logger.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            //logger.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

}
