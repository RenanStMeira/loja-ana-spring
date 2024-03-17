package com.analoja.artesanato.Controllers;

import com.analoja.artesanato.DTO.ClienteCreateDTO;
import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.services.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<Cliente>> findAll(Pageable pageable) throws RegraDeNegocioException {
        Page<Cliente> clientes = clienteService.findAll(pageable);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<Cliente> cadatrarClienteResponseEntity(@RequestBody ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {
        Cliente cliente = clienteService.cadastrarCliente(clienteCreateDTO);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{idCliente}")
    public ResponseEntity<Cliente> atualizarClienteResponseEntity(@PathVariable Integer idCliente, @RequestBody ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {
        Cliente cliente = clienteService.atualizarCliente(idCliente, clienteCreateDTO);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }
}
