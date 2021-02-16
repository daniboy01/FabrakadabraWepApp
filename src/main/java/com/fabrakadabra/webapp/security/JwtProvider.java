package com.fabrakadabra.webapp.security;

import com.fabrakadabra.webapp.exception.SpringFabrakadabraException;
import io.jsonwebtoken.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.sql.Date;
import java.time.Instant;

import static io.jsonwebtoken.Jwts.parser;
import static java.util.Date.from;


@Service
public class JwtProvider {
    private KeyStore keyStore;

    @Value("${jwt.expiration.time}")
    private Long jwtExpirationMillis;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/springblog.jks");
            keyStore.load(resourceAsStream, "secret".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new SpringFabrakadabraException("Exception occurred while loading keystore");
        }

    }

    public String generateToken(Authentication authentication){
        org.springframework.security.core.userdetails.User principal = (User)authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(principal.getUsername())
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(from(Instant.now().plusMillis(jwtExpirationMillis)))
                .compact();
    }

    public String generateTokenWithUserName(String name){
        return Jwts.builder()
                .setSubject(name)
                .setIssuedAt(from(Instant.now()))
                .signWith(getPrivateKey())
                .setExpiration(from(Instant.now().plusMillis(jwtExpirationMillis)))
                .compact();
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("springblog", "secret".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new SpringFabrakadabraException("Exception occured while retrieving public key from keystore");
        }
    }

    public boolean validateToken(String jwt, HttpServletResponse response) throws IOException {
        try {
            parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
            return true;
        }catch (ExpiredJwtException ex){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Jwt token expired!");
        }catch (UnsupportedJwtException ex){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unsupported JWT exception");
        }catch (IllegalArgumentException ex){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Jwt claims string is empty");
        }
        return false;
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("springblog").getPublicKey();
        } catch (KeyStoreException e) {
            throw new SpringFabrakadabraException("Exception occured while retrieving public key from keystore");
        }
    }

    public String getUsernameFromJwt(String token){
        Claims claims = parser()
                .setSigningKey(getPublicKey())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    public Long getJwtExpirationMillis() {
        return jwtExpirationMillis;
    }
}
