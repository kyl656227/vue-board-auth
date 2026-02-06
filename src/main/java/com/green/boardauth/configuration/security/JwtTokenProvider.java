package com.green.boardauth.configuration.security;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.green.boardauth.configuration.constants.ConstJwt;
import com.green.boardauth.configuration.model.JwtUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import javax.crypto.SecretKey;
import javax.swing.plaf.PanelUI;
import java.util.Date;
import java.util.SimpleTimeZone;

@Slf4j
@Component // 아무 역할 없는 빈등록
public class JwtTokenProvider {
    private  final ObjectMapper objectMapper; //(내장)jackson 라이브러리 di받을 속성
    private final ConstJwt constJwt;
    private final SecretKey secretKey;

    public JwtTokenProvider(ObjectMapper objectMapper,ConstJwt constJwt){
        this.objectMapper = objectMapper;
        this.constJwt = constJwt;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(constJwt.getSecretKey()));

        log.info("constJwt: {}", this.constJwt);

    }
    public String generateAccessToken(JwtUser jwtUser){
        return generateToken(jwtUser, constJwt.getAccessTokenValidityMilliseconds());
    }

    public String generateRefreshToken(JwtUser jwtUser){
        return generateToken(jwtUser, constJwt.getRefreshTokenValidityMilliseconds());
    }

    //JWT 문자열을 만드는 메소드 , 암호화된 문자열임(안에 데이터랑 토큰 만료시간 들어있음)
    public String generateToken(JwtUser jwtUser, long tokenValidityMilliSeconds){
        Date now = new Date();
        return Jwts.builder()
                //header
                .header().type(constJwt.getBearerFormat())//JWT
                .and()
                //payload
                .issuer(constJwt.getIssuer())
                .issuedAt( now ) //JWT만든 일시 (토큰 생성일)
                .expiration( new Date(now.getTime() + tokenValidityMilliSeconds)) //JWT종료 일시(토큰 만료 일시)
                .claim(constJwt.getClaimKey(),makeClaimByUserToJson(jwtUser))//signedUser 키값으로 JwtUser 객체를 json으로 변환해서 담음

                .signWith(secretKey) //signature
                .compact();
    }

    public String makeClaimByUserToJson(JwtUser jwtUser){ //직렬화 (Serialization)
            return objectMapper.writeValueAsString(jwtUser); // 객체 -> JSON 문자열로 변환

    }

    public JwtUser getJwtUserFromToken(String token){
        Claims claims = getClaims(token);

        //signeduser 키값으로 담겨져 있는 string 타입으로 리턴
        String json = claims.get(constJwt.getClaimKey(), String.class);

        //JSON > Object, json문자열을 JwtUser 객체로 변환
        return objectMapper.readValue(json, JwtUser.class);

    }

    private Claims getClaims(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


}
