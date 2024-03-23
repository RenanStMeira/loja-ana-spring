package com.analoja.artesanato.Controllers.interfaces;

import com.analoja.artesanato.DTO.Avaliacao.AvaliacaoCreateDTO;
import com.analoja.artesanato.entity.Avaliacao;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public interface AvaliacaoControllerInterface {

    @Operation(summary = "List all evaluations")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluations retrieved successfully"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping
    ResponseEntity<Page<Avaliacao>> listarTodasAvaliacoes(Pageable pageable) throws RegraDeNegocioException;

    @Operation(summary = "Find evaluation by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluation found"),
            @ApiResponse(responseCode = "404", description = "Evaluation not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/{idAvaliacao}")
    ResponseEntity<Avaliacao> buscarAvaliacaoPorId(@PathVariable Integer idAvaliacao) throws RegraDeNegocioException;

    @Operation(summary = "Create a new evaluation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Evaluation created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PostMapping
    ResponseEntity<Avaliacao> criarAvaliacao(@RequestBody AvaliacaoCreateDTO avaliacao) throws RegraDeNegocioException;

    @Operation(summary = "Update an existing evaluation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluation updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Evaluation not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @PutMapping("/{idAvaliacao}")
    ResponseEntity<Avaliacao> atualizarAvaliacao(@PathVariable Integer idAvaliacao, @RequestBody AvaliacaoCreateDTO avaliacao) throws RegraDeNegocioException;

    @Operation(summary = "Delete an evaluation")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evaluation deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Evaluation not found"),
            @ApiResponse(responseCode = "500", description = "An exception occurred")
    })
    @GetMapping("/deletar/{idAvaliacao}")
    void deletarAvaliacao(@PathVariable Integer idAvaliacao) throws RegraDeNegocioException;
}
