package com.analoja.artesanato.Controllers.interfaces;

import com.analoja.artesanato.DTO.Login.LoginDTO;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

public interface LoginControllerInterface {

    @PostMapping
    ResponseEntity<String> auth(@RequestBody @Valid LoginDTO loginDTO) throws RegraDeNegocioException;
}
