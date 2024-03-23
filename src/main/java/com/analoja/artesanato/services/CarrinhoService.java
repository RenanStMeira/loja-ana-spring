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

    public Carrinho atualizarItem(Integer idCarrinho, Integer idItemCarrinho, Integer idProduto, Integer quantidade) throws RegraDeNegocioException {
        Carrinho carrinho = carrinhoRepository.findById(idCarrinho)
                .orElseThrow(() -> new RegraDeNegocioException("Carrinho não encontrado"));

        ItemCarrinho item = itemCarrinhoRepository.findById(idItemCarrinho)
                .orElseThrow(() -> new RegraDeNegocioException("Item do carrinho não encontrado"));

        if (!item.getCarrinho().getIdCarrinho().equals(idCarrinho)) {
            throw new RegraDeNegocioException("Item do carrinho não pertence ao carrinho fornecido");
        }

        Produto produto = produtoService.findById(idProduto);

        item.setProduto(produto);
        item.setQuantidade(quantidade);

        item = itemCarrinhoRepository.save(item);

        return carrinho;
    }

    public Carrinho removerItem(Integer idCarrinho, Integer idItemCarrinho) throws RegraDeNegocioException {
        Carrinho carrinho = carrinhoRepository.findById(idCarrinho)
                .orElseThrow(() -> new RegraDeNegocioException("Carrinho não encontrado"));

        ItemCarrinho item = itemCarrinhoRepository.findById(idItemCarrinho)
                .orElseThrow(() -> new RegraDeNegocioException("Item do carrinho não encontrado"));

        if (!item.getCarrinho().getIdCarrinho().equals(idCarrinho)) {
            throw new RegraDeNegocioException("Item do carrinho não pertence ao carrinho fornecido");
        }

        itemCarrinhoRepository.delete(item);

        return carrinho;
    }

    public Carrinho buscarPorId(Integer idCarrinho) throws RegraDeNegocioException {
        return carrinhoRepository.findById(idCarrinho)
                .orElseThrow(() -> new RegraDeNegocioException("Carrinho não encontrado"));
    }

    public Carrinho criar(CarrinhoCreateDTO carrinhoCreateDTO) throws RegraDeNegocioException {
        Cliente cliente = clienteService.buscarPorId(carrinhoCreateDTO.getClienteId());

        Carrinho carrinho = new Carrinho();
        carrinho.setCliente(cliente);

        carrinho = carrinhoRepository.save(carrinho);

        Produto produto = produtoService.findById(carrinhoCreateDTO.getProdutoId());

        ItemCarrinho item = new ItemCarrinho();
        item.setProduto(produto);
        item.setQuantidade(carrinhoCreateDTO.getQuantidade());
        item.setCarrinho(carrinho);

        item = itemCarrinhoRepository.save(item);

        carrinho.getItens().add(item);

        return carrinhoRepository.save(carrinho);
    }

    public void deletar(Integer idCarrinho) {
        carrinhoRepository.deleteById(idCarrinho);
    }
}
