package com.analoja.artesanato.Controllers.interfaces;

import com.analoja.artesanato.DTO.Pedido.PedidoCreateDTO;
import com.analoja.artesanato.entity.Pedido;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PedidoControllerInterface {

    @Operation(summary = "Get order by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/{id}")
    ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Integer id) throws RegraDeNegocioException;

    @Operation(summary = "Get all orders")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping
    ResponseEntity<?> buscarTodosPedidos() throws RegraDeNegocioException;

    @Operation(summary = "Update an existing order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PutMapping("/{id}")
    ResponseEntity<Pedido> atualizarPedido(@PathVariable Integer id, @RequestBody PedidoCreateDTO pedidoDTO) throws RegraDeNegocioException;

    @Operation(summary = "Create a new order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PostMapping
    ResponseEntity<Pedido> criarPedido(@RequestBody PedidoCreateDTO pedidoDTO) throws RegraDeNegocioException;

    @Operation(summary = "Delete an order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Order not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @DeleteMapping("/{id}")
    ResponseEntity<?> deletarPedido(@PathVariable Integer id) throws RegraDeNegocioException;
}