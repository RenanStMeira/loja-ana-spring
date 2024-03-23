package com.analoja.artesanato.Controllers.interfaces;

import com.analoja.artesanato.DTO.Pagamento.PagamentoCreateDTO;
import com.analoja.artesanato.entity.Pagamento;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PagamentoControllerInterface {

    @Operation(summary = "Get all payments")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payments retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping
    ResponseEntity<?> buscarTodosPagamentos() throws RegraDeNegocioException;

    @Operation(summary = "Get payment by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Payment not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/{id}")
    ResponseEntity<Pagamento> buscarPagamentoPorId(@PathVariable Integer id) throws RegraDeNegocioException;

    @Operation(summary = "Update an existing payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Payment not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PutMapping("/{id}")
    ResponseEntity<Pagamento> atualizarPagamento(@PathVariable Integer id, @RequestBody PagamentoCreateDTO pagamentoCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Create a new payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PostMapping
    ResponseEntity<Pagamento> criarPagamento(@RequestBody PagamentoCreateDTO pagamentoCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Delete a payment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Payment not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> deletarPagamento(@PathVariable Integer id) throws RegraDeNegocioException;
}