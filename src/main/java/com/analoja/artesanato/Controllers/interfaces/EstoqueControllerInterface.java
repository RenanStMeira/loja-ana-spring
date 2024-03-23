package com.analoja.artesanato.Controllers.interfaces;

import com.analoja.artesanato.DTO.Estoque.EstoqueCreateDTO;
import com.analoja.artesanato.entity.Estoque;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface EstoqueControllerInterface {

    @Operation(summary = "Add product to stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product added successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PostMapping("/adicionar")
    ResponseEntity<Estoque> adicionarProduto(@RequestBody EstoqueCreateDTO dto);

    @Operation(summary = "Update stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stock updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PutMapping("/atualizar")
    ResponseEntity<Estoque> atualizarEstoque(@RequestBody EstoqueCreateDTO dto);

    @Operation(summary = "Check stock for a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Stock retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/verificar/{produtoId}")
    ResponseEntity<Estoque> verificarEstoque(@PathVariable Integer produtoId);

    @Operation(summary = "Remove product from stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product removed successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @DeleteMapping("/remover/{produtoId}")
    ResponseEntity<?> removerProduto(@PathVariable Integer produtoId);

    @Operation(summary = "Check if product quantity is below minimum")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Quantity checked successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/verificar-quantidade-minima/{produtoId}")
    ResponseEntity<Boolean> verificarQuantidadeMinima(@PathVariable Integer produtoId);
}