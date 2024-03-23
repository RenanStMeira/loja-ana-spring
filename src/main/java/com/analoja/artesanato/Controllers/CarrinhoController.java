package com.analoja.artesanato.Controllers;

import com.analoja.artesanato.Controllers.interfaces.CarrinhoControllerInterface;
import com.analoja.artesanato.DTO.Carrinho.CarrinhoCreateDTO;
import com.analoja.artesanato.entity.Carrinho;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.services.CarrinhoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@Tag(name = "Carrinho", description = "Endpoint de Carrinho")
@RequestMapping("/carrinho")
public class CarrinhoController implements CarrinhoControllerInterface {

    private final CarrinhoService carrinhoService;

    @PostMapping
    public ResponseEntity<Carrinho> adicionarItem(Integer idProduto, Integer quantidade, Integer idCliente) throws RegraDeNegocioException {
        return ResponseEntity.ok(carrinhoService.adicionarItem( idProduto, quantidade, idCliente));
    }

    @PutMapping("/{idCarrinho}/item/{idItemCarrinho}")
    public ResponseEntity<Carrinho> atualizarItem(@PathVariable Integer idCarrinho, @PathVariable Integer idItemCarrinho, Integer idProduto, Integer quantidade) throws RegraDeNegocioException {
        return ResponseEntity.ok(carrinhoService.atualizarItem(idCarrinho, idItemCarrinho, idProduto, quantidade));
    }

    @DeleteMapping("/{idCarrinho}/item/{idItemCarrinho}")
    public ResponseEntity<Carrinho> removerItem(@PathVariable Integer idCarrinho, @PathVariable Integer idItemCarrinho) throws RegraDeNegocioException {
        return ResponseEntity.ok(carrinhoService.removerItem(idCarrinho, idItemCarrinho));
    }

    @GetMapping("/{idCarrinho}")
    public ResponseEntity<Carrinho> buscarPorId(@PathVariable Integer idCarrinho) throws RegraDeNegocioException {
        return ResponseEntity.ok(carrinhoService.buscarPorId(idCarrinho));
    }

    @PostMapping("/criar")
    public ResponseEntity<Carrinho> criar(@RequestBody CarrinhoCreateDTO carrinhoCreateDTO) throws RegraDeNegocioException {
        return ResponseEntity.ok(carrinhoService.criar(carrinhoCreateDTO));
    }

    @DeleteMapping("/{idCarrinho}")
    public ResponseEntity<Void> deletar(@PathVariable Integer idCarrinho) {
        carrinhoService.deletar(idCarrinho);
        return ResponseEntity.noContent().build();
    }
}
