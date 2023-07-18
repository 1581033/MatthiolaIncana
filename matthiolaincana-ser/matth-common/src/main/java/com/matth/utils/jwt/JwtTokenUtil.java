package com.matth.utils.jwt;

import com.matth.utils.date.DateUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * @author Matthiola incana
 * @create 2021/10/14 18:10
 */
public class JwtTokenUtil {

    private static final String ISSUER = "WUYUWEI_BACK_API";

    /**
    * Token加密解密的密码
    */
    private static final String TOKEN_SECRET = "pwdddd";

    public static final String TOKENNAME = "Access-Token";

    public JwtTokenUtil() { }

    public static String creatToken(String username, String tokenSecret){
        return  Jwts.builder()
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setNotBefore(new Date())
                .setIssuer(ISSUER)
                .setAudience(username)
                .setExpiration(DateUtils.parseDate(LocalDateTime.now().plusHours(12)))
                .signWith(SignatureAlgorithm.HS256,tokenSecret)
                .compact();
    }

    public static Boolean verifyToken(String token, String tokenSecret) {
        try {
            Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token);
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public static String getTokenUserName(String token,String tokenSecret) {
        return Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody().getAudience();
    }

}
