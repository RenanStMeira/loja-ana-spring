package com.analoja.artesanato.services;

import com.analoja.artesanato.DTO.Carrinho.CarrinhoCreateDTO;
import com.analoja.artesanato.entity.Carrinho;
import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.entity.ItemCarrinho;
import com.analoja.artesanato.entity.Produto;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.repository.CarrinhoRepository;
import com.analoja.artesanato.repository.ItemCarrinhoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class CarrinhoService {

    private final ProdutoService produtoService;
    private final ClienteService clienteService;
    private final ItemCarrinhoRepository itemCarrinhoRepository;
    private final CarrinhoRepository carrinhoRepository;

    public Carrinho adicionarItem(Integer idCliente, Integer idProduto, Integer quantidade) throws RegraDeNegocioException {
        Cliente cliente = clienteService.buscarPorId(idCliente);

        Carrinho carrinho = new Carrinho();
        carrinho.setCliente(cliente);

        carrinho = carrinhoRepository.save(carrinho);

        Produto produto = produtoService.findById(idProduto);

        ItemCarrinho item = new ItemCarrinho();
        item.setProduto(produto);
        item.setQuantidade(quantidade);
        item.setCarrinho(carrinho);

        item = itemCarrinhoRepository.save(item);

        carrinho.getItens().add(item);

        return carrinhoRepository.save(carrinho);
    }
}
