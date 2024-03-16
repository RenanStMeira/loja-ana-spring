package com.analoja.artesanato.entity;

import com.analoja.artesanato.enums.Categoria;
import com.analoja.artesanato.enums.Cor;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(schema = "LojaAna", name = "PRODUTO")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idProduto;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "PRECO")
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    @Enumerated(EnumType.STRING)
    private Cor cor;
}