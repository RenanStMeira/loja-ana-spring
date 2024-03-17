package com.analoja.artesanato.services;

import com.analoja.artesanato.DTO.ProdutoCreateDTO;
import com.analoja.artesanato.entity.Produto;
import com.analoja.artesanato.enums.Categoria;
import com.analoja.artesanato.enums.Cor;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.repository.ProdutoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final ObjectMapper objectMapper;

    private String MESSAGE_PRODUTO_NAO_ENCONTRADO = "Produto não encontrado";
    private String MESSAGE_ID_NAO_ENCONTRADO = "ID não encontrado";

    public Produto create(ProdutoCreateDTO produtoDTO, Categoria categoria, Cor cor) throws RegraDeNegocioException {
        Produto produto = new Produto();

        produto.setNome(produtoDTO.getNome());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setCategoria(categoria);
        produto.setCor(cor);

        return produtoRepository.save(produto);
    }

    public Produto update(Integer idProduto, ProdutoCreateDTO produtoDTO, Categoria categoria, Cor cor) throws RegraDeNegocioException {
        existsId(idProduto);
        Produto produtoRecuperado = findById(idProduto);

        produtoRecuperado.setNome(produtoDTO.getNome());
        produtoRecuperado.setDescricao(produtoDTO.getDescricao());
        produtoRecuperado.setPreco(produtoDTO.getPreco());
        produtoRecuperado.setCategoria(categoria);
        produtoRecuperado.setCor(cor);

        return produtoRepository.save(produtoRecuperado);
    }

    public Page<Produto> findAll(Pageable pageable) {
        Pageable pageableOrdenadoPorId = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("idProduto"));
        return produtoRepository.findAll(pageableOrdenadoPorId);
    }

    public Page<Produto> findByCor(Cor cor, Pageable pageable) {
        Pageable pageableOrdenadoPorId = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("idProduto"));
        return produtoRepository.findByCor(cor, pageableOrdenadoPorId);
    }

    public Produto findById(Integer idProduto) throws RegraDeNegocioException {
        existsId(idProduto);
        return produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RegraDeNegocioException( MESSAGE_PRODUTO_NAO_ENCONTRADO ));
    }

    public Page<Produto> listByCategoria(Categoria categoria, Pageable pageable) {
        return produtoRepository.findByCategoria(categoria, pageable);
    }

    public void delete(Integer idProduto) throws RegraDeNegocioException {
        existsId(idProduto);
        Produto produto = findById(idProduto);
        produtoRepository.delete(produto);
    }

    private Produto existsId(Integer idProduto) throws RegraDeNegocioException {
        return produtoRepository.findById(idProduto)
                .orElseThrow(() -> new RegraDeNegocioException(MESSAGE_ID_NAO_ENCONTRADO));
    }

}
