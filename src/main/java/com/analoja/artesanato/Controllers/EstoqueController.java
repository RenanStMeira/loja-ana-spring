package com.analoja.artesanato.Controllers;

import com.analoja.artesanato.Controllers.interfaces.EstoqueControllerInterface;
import com.analoja.artesanato.DTO.Estoque.EstoqueCreateDTO;
import com.analoja.artesanato.entity.Estoque;
import com.analoja.artesanato.services.EstoqueService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor
@Tag(name = "Estoque", description = "Endpoint de Estoque")
@RequestMapping("/estoque")
public class EstoqueController implements EstoqueControllerInterface {

    private final EstoqueService estoqueService;

    @PostMapping("/adicionar")
    public ResponseEntity<Estoque> adicionarProduto(@Valid @RequestBody EstoqueCreateDTO dto) {
        Estoque estoque = estoqueService.adicionarProduto(dto);
        return new ResponseEntity<>(estoque, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<Estoque> atualizarEstoque(@Valid @RequestBody EstoqueCreateDTO dto) {
        Estoque estoque = estoqueService.atualizarEstoque(dto);
        return new ResponseEntity<>(estoque, HttpStatus.OK);
    }

    @GetMapping("/verificar/{produtoId}")
    public ResponseEntity<Estoque> verificarEstoque(@PathVariable Integer produtoId) {
        Estoque estoque = estoqueService.verificarEstoque(produtoId);
        return new ResponseEntity<>(estoque, HttpStatus.OK);
    }

    @DeleteMapping("/remover/{produtoId}")
    public ResponseEntity<?> removerProduto(@PathVariable Integer produtoId) {
        estoqueService.removerProduto(produtoId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/verificar-quantidade-minima/{produtoId}")
    public ResponseEntity<Boolean> verificarQuantidadeMinima(@PathVariable Integer produtoId) {
        boolean isBelowMinimum = estoqueService.verificarQuantidadeMinima(produtoId);
        return new ResponseEntity<>(isBelowMinimum, HttpStatus.OK);
    }
}