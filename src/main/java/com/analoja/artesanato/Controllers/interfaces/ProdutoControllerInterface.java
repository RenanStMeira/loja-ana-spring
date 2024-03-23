package com.analoja.artesanato.Controllers.interfaces;

import com.analoja.artesanato.DTO.Produto.ProdutoCreateDTO;
import com.analoja.artesanato.entity.Produto;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Produto", description = "Endpoint de Produto")
public interface ProdutoControllerInterface {

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping
    ResponseEntity<Page<Produto>> findAll(Pageable pageable);

    @Operation(summary = "Get products by color")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/cor")
    ResponseEntity<Page<Produto>> findByCor(@RequestParam("cor") String corStr, Pageable pageable);

    @Operation(summary = "Get product by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/{idProduto}")
    ResponseEntity<Produto> findById(@PathVariable("idProduto") Integer idProduto) throws RegraDeNegocioException;

    @Operation(summary = "Get products by category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/categoria")
    ResponseEntity<Page<Produto>> listByCategoria(@RequestParam("categoria") String categoriaStr, Pageable pageable) throws RegraDeNegocioException;

    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PostMapping("/create")
    ResponseEntity<Produto> create(@RequestParam("categoria") String categoriaStr, @RequestParam("cor") String corStr, @RequestBody @Valid ProdutoCreateDTO produtoDTO) throws RegraDeNegocioException;

    @Operation(summary = "Update an existing product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PutMapping("/update/{idProduto}")
    ResponseEntity<Produto> update(@PathVariable("idProduto") Integer idProduto, @RequestParam("categoria") String categoriaStr, @RequestParam("cor") String corStr, @RequestBody @Valid ProdutoCreateDTO produtoDTO) throws RegraDeNegocioException;

    @Operation(summary = "Delete a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @DeleteMapping("/delete/{idProduto}")
    ResponseEntity<?> delete(@PathVariable("idProduto") Integer idProduto) throws RegraDeNegocioException;
}