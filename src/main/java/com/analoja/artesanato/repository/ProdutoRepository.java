package com.analoja.artesanato.repository;

import com.analoja.artesanato.entity.Produto;
import com.analoja.artesanato.enums.Categoria;
import com.analoja.artesanato.enums.Cor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    Page<Produto> findByCategoria(Categoria categoria, Pageable pageable);

    Page<Produto> findByCor(Cor cor, Pageable pageableOrdenadoPorId);
}
