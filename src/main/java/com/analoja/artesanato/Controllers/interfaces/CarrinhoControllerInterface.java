package com.analoja.artesanato.Controllers.interfaces;

import com.analoja.artesanato.DTO.Carrinho.CarrinhoCreateDTO;
import com.analoja.artesanato.entity.Carrinho;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CarrinhoControllerInterface {

    @Operation(summary = "Add item to cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item added successfully"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PostMapping
    ResponseEntity<Carrinho> adicionarItem(Integer idProduto, Integer quantidade, Integer idCliente) throws RegraDeNegocioException;

    @Operation(summary = "Update item in cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item updated successfully"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PutMapping("/{idCarrinho}/item/{idItemCarrinho}")
    ResponseEntity<Carrinho> atualizarItem(@PathVariable Integer idCarrinho, @PathVariable Integer idItemCarrinho, Integer idProduto, Integer quantidade) throws RegraDeNegocioException;

    @Operation(summary = "Remove item from cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Item removed successfully"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @DeleteMapping("/{idCarrinho}/item/{idItemCarrinho}")
    ResponseEntity<Carrinho> removerItem(@PathVariable Integer idCarrinho, @PathVariable Integer idItemCarrinho) throws RegraDeNegocioException;

    @Operation(summary = "Find cart by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart found"),
            @ApiResponse(responseCode = "404", description = "Cart not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/{idCarrinho}")
    ResponseEntity<Carrinho> buscarPorId(@PathVariable Integer idCarrinho) throws RegraDeNegocioException;

    @Operation(summary = "Create a new cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart created successfully"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PostMapping("/criar")
    ResponseEntity<Carrinho> criar(@RequestBody CarrinhoCreateDTO carrinhoCreateDTO) throws RegraDeNegocioException;

    @Operation(summary = "Delete a cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cart deleted successfully"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @DeleteMapping("/{idCarrinho}")
    ResponseEntity<Void> deletar(@PathVariable Integer idCarrinho);
}
