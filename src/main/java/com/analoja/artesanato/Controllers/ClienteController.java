package com.analoja.artesanato.Controllers;

import com.analoja.artesanato.DTO.ClienteCreateDTO;
import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> cadatrarClienteResponseEntity(@RequestBody ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {
        Cliente cliente = clienteService.cadastrarCliente(clienteCreateDTO);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }
}
