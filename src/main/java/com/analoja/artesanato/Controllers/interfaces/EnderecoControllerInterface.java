package com.analoja.artesanato.Controllers.interfaces;

import com.analoja.artesanato.DTO.Endereco.EnderecoCreateDTO;
import com.analoja.artesanato.DTO.Endereco.EnderecoResponseDTO;
import com.analoja.artesanato.entity.Endereco;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface EnderecoControllerInterface {

    @Operation(summary = "List all addresses")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Addresses retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping
    ResponseEntity<List<Endereco>> listarEnderecos() throws RegraDeNegocioException;

    @Operation(summary = "Find address by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address found"),
            @ApiResponse(responseCode = "404", description = "Address not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/{idEndereco}")
    ResponseEntity<EnderecoResponseDTO> buscarPorId(@PathVariable Integer idEndereco) throws RegraDeNegocioException;

    @Operation(summary = "Create a new address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Address created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PostMapping("/create")
    ResponseEntity<EnderecoResponseDTO> criarEndereco(@RequestBody EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Update an existing address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Address updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Address not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PutMapping("/update/{idEndereco}")
    ResponseEntity<EnderecoResponseDTO> atualizarEndereco(@PathVariable Integer idEndereco, @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Delete an address")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Address deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Address not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @DeleteMapping("/delete/{idEndereco}")
    ResponseEntity<Void> deletarEndereco(@PathVariable Integer idEndereco) throws RegraDeNegocioException;
}
