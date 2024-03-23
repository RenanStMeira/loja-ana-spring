package com.analoja.artesanato.Controllers;

import com.analoja.artesanato.entity.Carrinho;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.services.CarrinhoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;

    @PostMapping
    public ResponseEntity<Carrinho> adicionarItem(Integer idProduto, Integer quantidade, Integer idCliente) throws RegraDeNegocioException {
        return ResponseEntity.ok(carrinhoService.adicionarItem( idProduto, quantidade, idCliente));
    }
}
