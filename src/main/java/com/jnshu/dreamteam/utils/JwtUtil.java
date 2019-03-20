package com.jnshu.dreamteam.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final  String SECRET_KEY = "b4fb96f10e5c28b10c0c8cf93c032cad";
    /**
     * token加密
     */
    public static String createToken(Map<String,Object> map){
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis()+10*24*60*60000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes());
        return builder.compact();
    }

    /**
     * token解密
     */
    public static Object decodeToken(String token,String fieldName){
        try {
            return Jwts.parser()
                   .setSigningKey(SECRET_KEY.getBytes())
                   .parseClaimsJws(token).getBody()
                   .get(fieldName);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 无需secretKey也可以解密
     * @param token
     * @return
     */
    public static Claim getClaims(String token, String param){
         try {
             DecodedJWT jwt = JWT.decode(token);
             return jwt.getClaim(param);
         }catch (JWTDecodeException e){
             return null;
         }
    }

    /**
     * 校验token是否正确
     * @param token 密钥
     * @return 是否正确
     */
    public static boolean verify(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (Exception exception) {
            return false;
        }
    }

}
