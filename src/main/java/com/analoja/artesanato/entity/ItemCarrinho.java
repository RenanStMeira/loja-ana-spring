package com.analoja.artesanato.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "ItemCarrinho")
public class ItemCarrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Integer idItemCarrinho;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "carrinho_id")
    private Carrinho carrinho;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;
}