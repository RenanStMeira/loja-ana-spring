package com.analoja.artesanato.services;

import com.analoja.artesanato.DTO.Pedido.PedidoCreateDTO;
import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.entity.Pedido;
import com.analoja.artesanato.entity.Produto;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.repository.ClienteRepository;
import com.analoja.artesanato.repository.PedidoRepository;
import com.analoja.artesanato.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PedidoService {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public Pedido criarPedido(PedidoCreateDTO pedidoDTO) throws RegraDeNegocioException {
        Pedido pedido = new Pedido();

        Cliente cliente = clienteRepository.findById(pedidoDTO.getCliente())
                .orElseThrow(() -> new RegraDeNegocioException("Cliente não encontrado"));
        pedido.setCliente(cliente);

        Produto produto = produtoRepository.findById(pedidoDTO.getIdProduto())
                .orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado"));
        pedido.setProduto(produto);

        pedido.setData(pedidoDTO.getData());
        pedido.setQuantidade(pedidoDTO.getQuantidade());

        BigDecimal precoTotal = produto.getPreco().multiply(new BigDecimal(pedidoDTO.getQuantidade()));
        pedido.setPreco_total(precoTotal);

        pedido.setStatus(pedidoDTO.getStatus());

        return pedidoRepository.save(pedido);
    }

    public Pedido atualizarPedido(Integer idPedido, PedidoCreateDTO pedidoDTO) throws RegraDeNegocioException {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RegraDeNegocioException("Pedido não encontrado"));

        Cliente cliente = clienteRepository.findById(pedidoDTO.getCliente())
                .orElseThrow(() -> new RegraDeNegocioException("Cliente não encontrado"));
        pedido.setCliente(cliente);

        Produto produto = produtoRepository.findById(pedidoDTO.getIdProduto())
                .orElseThrow(() -> new RegraDeNegocioException("Produto não encontrado"));
        pedido.setProduto(produto);

        pedido.setData(pedidoDTO.getData());
        pedido.setQuantidade(pedidoDTO.getQuantidade());

        BigDecimal precoTotal = produto.getPreco().multiply(new BigDecimal(pedidoDTO.getQuantidade()));
        pedido.setPreco_total(precoTotal);

        pedido.setStatus(pedidoDTO.getStatus());

        return pedidoRepository.save(pedido);
    }

    public Pedido buscarPedidoPorId(Integer idPedido) throws RegraDeNegocioException {
        return pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RegraDeNegocioException("Pedido não encontrado"));
    }

    public List<Pedido> buscarTodosPedidos() throws RegraDeNegocioException {
        return pedidoRepository.findAll();
    }

    public void deletarPedido(Integer idPedido) throws RegraDeNegocioException {
        pedidoRepository.deleteById(idPedido);
    }

}
