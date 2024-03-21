package com.analoja.artesanato.security;

import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.services.ClienteService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import static com.analoja.artesanato.security.TokenGenerator.TOKEN_PREFIX;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenGenerator tokenGenerator;

    public UsernamePasswordAuthenticationToken isValid(String token) {
        if (token != null) {
            Claims body = Jwts.parser()
                    .setSigningKey(tokenGenerator.getSecret())
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

    public String generateToken(Cliente usuarioValidado) {
        return TOKEN_PREFIX + tokenGenerator.generateToken(usuarioValidado);
    }
}