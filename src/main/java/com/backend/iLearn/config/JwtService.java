package com.backend.iLearn.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "84fe6d7bddfaa2becd1442b454853786ea7aed767c7017ec";
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token){
        System.out.println("*********************    12");
        System.out.println("All claims:::" + Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build().parseClaimsJws(token)
                .getBody());
        System.out.println("*********************    13");
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build().parseClaimsJws(token)
                .getBody();
    }

    private <T>T extractClaim(String token, Function<Claims, T> claimsResolver) {
        System.out.println("*********************    14");
        final Claims claims = extractAllClaims(token);
        System.out.println("claimsResolver.apply(claims) ::" + claimsResolver.apply(claims));
        System.out.println("*********************    15");
        return claimsResolver.apply(claims);
    }

//    public String generateToken( UserDetails userDetails){
//        return generateToken(new HashMap<>(), userDetails);
//    }
    public String generateToken( UserDetails userDetails){
        Map<String, Object> extractClaims = new HashMap<>();
        System.out.println("*********************    16");
        JwtBuilder jwtBuilder = Jwts.builder();
        System.out.println("*********************    17");
        JwtBuilder setJwtClaims = jwtBuilder.setClaims(extractClaims);
        System.out.println("*********************    18");
        JwtBuilder setJwtSubject =  setJwtClaims.setSubject(userDetails.getUsername());
        System.out.println("*********************    19");
        JwtBuilder setJwtIssuedAt = setJwtSubject.setIssuedAt(new Date(System.currentTimeMillis()));
        System.out.println("*********************    20");
        JwtBuilder setJwtExpiration = setJwtIssuedAt.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24));
        
        JwtBuilder sign = setJwtExpiration.signWith(getSignInKey(), SignatureAlgorithm.HS256);
        System.out.println("*********************    21");
        return sign.compact();
    }
    
    public boolean isTokenValid(String token, UserDetails userDetails){
        System.out.println("*********************    22");
        final String username = extractUsername(token);
        System.out.println("*********************    23");
        System.out.println("*********************    24" + (username.equals((userDetails.getUsername())) && !isTokenExired(token)));
        return (username.equals((userDetails.getUsername())) && !isTokenExired(token));
    }

    private boolean isTokenExired(String token) {
        System.out.println(extractExpiration(token).before(new Date()) + "Checking if token expired *********************    25");
        return  extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        System.out.println(extractClaim(token, Claims::getExpiration) + "extracting Expiration*********************    26");
        return extractClaim(token, Claims::getExpiration);
    }


    private Key getSignInKey() {
        System.out.println("getSignInKey *********************    27");
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        System.out.println(Keys.hmacShaKeyFor(keyBytes) + "Keys.hmacShaKeyFor(keyBytes) *********************    28");
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
