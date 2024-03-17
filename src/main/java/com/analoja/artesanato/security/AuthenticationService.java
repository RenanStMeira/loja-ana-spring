package com.analoja.artesanato.security;

import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements UserDetailsService {

    @Lazy
    private final ClienteService clienteService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Cliente> usuarioEntityOptional = clienteService.findByLogin(username);
        return usuarioEntityOptional
                .map(UserDetails.class::cast)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente inválido"));
    }
}