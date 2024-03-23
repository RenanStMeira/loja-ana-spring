package com.analoja.artesanato.Controllers.interfaces;

import com.analoja.artesanato.DTO.Cliente.ClienteCreateDTO;
import com.analoja.artesanato.DTO.Cliente.ClienteResponseDTO;
import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ClienteControllerInterface {

    @Operation(summary = "Find all clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Clients retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping
    ResponseEntity<Page<Cliente>> buscarTodosClientes(Pageable pageable) throws RegraDeNegocioException;

    @Operation(summary = "Find client by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/{idCliente}")
    ResponseEntity<Cliente> byscarPorId(@PathVariable("idCliente") Integer idCliente) throws RegraDeNegocioException;

    @Operation(summary = "Find client by email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/buscarPorEmail/{email}")
    ResponseEntity<Cliente> buscarPorEmail(@PathVariable("email") String email) throws RegraDeNegocioException;

    @Operation(summary = "Find client by CPF")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/buscarPorCpf/{cpf}")
    ResponseEntity<Cliente> buscarPorCpf(@PathVariable("cpf") String cpf) throws RegraDeNegocioException;

    @Operation(summary = "Register a new client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PostMapping("/cadastrar")
    ResponseEntity<ClienteResponseDTO> cadatrarClienteResponseEntity(@RequestBody ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Update an existing client")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Client not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PutMapping("/atualizar/{idCliente}")
    ResponseEntity<Cliente> atualizarClienteResponseEntity(@PathVariable Integer idCliente, @RequestBody ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException;

    @DeleteMapping("/deletar/{idCliente}")
    ResponseEntity<Void> deletarClienteResponseEntity(@PathVariable Integer idCliente) throws RegraDeNegocioException;
}