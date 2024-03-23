package com.analoja.artesanato.Controllers;


import com.analoja.artesanato.Controllers.interfaces.LoginControllerInterface;
import com.analoja.artesanato.DTO.Login.LoginDTO;
import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.security.TokenService;
import com.analoja.artesanato.services.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/Login")
@Validated
@Tag(name = "Login", description = "Endpoint de Login")
@RequiredArgsConstructor
@Slf4j
public class LoginController implements LoginControllerInterface {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<String> auth(@RequestBody @Valid LoginDTO loginDTO) {
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                            loginDTO.getEmail(),
                            loginDTO.getSenha()
                    );
            Authentication authentication =
                    authenticationManager.authenticate(
                            usernamePasswordAuthenticationToken);
            Cliente usuarioValidado = (Cliente) authentication.getPrincipal();
            return ResponseEntity.ok(tokenService.generateToken(usuarioValidado));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
