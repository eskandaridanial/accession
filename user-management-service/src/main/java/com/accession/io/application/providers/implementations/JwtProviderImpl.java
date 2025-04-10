package com.accession.io.application.providers.implementations;

import com.accession.io.application.providers.JwtProvider;
import com.accession.io.domain.entities.common.BaseCredential;
import com.accession.io.message.models.JwtModel;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;

/**
 * @author: Danial Eskandari
 * @createdAt: 2024-11-11 13:27:00
 */
@Component
public class JwtProviderImpl implements JwtProvider {

    @Value("${jwt.token.secret-key}")
    private String secretKey;

    @Value("${jwt.token.expire-time}")
    private long expireTime;

    private Key signingKey;

    @PostConstruct
    public void init() {
        this.signingKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    @Override
    public Claims extract(String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(signingKey)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
    }

    @Override
    public JwtModel convert(String accessToken) {
        Claims claims = extract(accessToken);
        return new JwtModel(
                claims.getId(),
                claims.getSubject(),
                claims.getIssuedAt(),
                claims.getExpiration(),
                accessToken
        );
    }

    @Override
    public <T extends BaseCredential> String issue(T credential, Map<String, Object> claims) {
        return Jwts.builder()
                .setId(credential.getSessionId().getSessionId())
                .setSubject(credential.getSubject().getSubject())
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(signingKey)
                .compact();
    }
}
