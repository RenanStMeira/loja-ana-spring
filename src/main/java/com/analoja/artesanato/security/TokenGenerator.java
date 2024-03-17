package com.analoja.artesanato.security;

import com.analoja.artesanato.entity.Cliente;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenGenerator {
    static final String HEADER_STRING = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Cliente cliente) {
        Date now = new Date();
        Date exp = new Date(now.getTime() + Long.parseLong(expiration));

        return TOKEN_PREFIX + " " +
                Jwts.builder()
                        .setIssuer("loja-apii")
                        .claim(Claims.ID, cliente.getIdCliente().toString())
                        .setIssuedAt(now)
                        .setExpiration(exp)
                        .signWith(SignatureAlgorithm.HS256, secret)
                        .compact();
    }

    public String getSecret() {
        return secret;
    }

    public UsernamePasswordAuthenticationToken isValid(String token) {
        if (token != null) {
            Claims body = Jwts.parser()
                    .setSigningKey(getSecret())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody();
            String user = body.get(Claims.ID, String.class);
            if (user != null) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(user, null, null);
                return usernamePasswordAuthenticationToken;
            }
        }
        return null;
    }
}