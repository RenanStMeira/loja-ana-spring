package com.analoja.artesanato.services;

import com.analoja.artesanato.DTO.Estoque.EstoqueCreateDTO;
import com.analoja.artesanato.entity.Estoque;
import com.analoja.artesanato.repository.EstoqueRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;

    public Estoque adicionarProduto(EstoqueCreateDTO dto) {
        Estoque estoque = new Estoque();
        estoque.setProduto(dto.getProduto());
        estoque.setQuantidade(dto.getQuantidade());
        estoque.setQuantidade_minima(dto.getQuantidade_minima());
        return estoqueRepository.save(estoque);
    }

    public Estoque atualizarEstoque(EstoqueCreateDTO dto) {
        Estoque estoque = estoqueRepository.findById(dto.getProduto().getIdProduto())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado no estoque"));
        estoque.setQuantidade(dto.getQuantidade());
        return estoqueRepository.save(estoque);
    }

    public Estoque verificarEstoque(Integer produtoId) {
        return estoqueRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado no estoque"));
    }

    public void removerProduto(Integer produtoId) {
        Estoque estoque = estoqueRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado no estoque"));
        estoqueRepository.delete(estoque);
    }

    public boolean verificarQuantidadeMinima(Integer produtoId) {
        Estoque estoque = estoqueRepository.findById(produtoId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado no estoque"));
        return estoque.getQuantidade() < estoque.getQuantidade_minima();
    }
}