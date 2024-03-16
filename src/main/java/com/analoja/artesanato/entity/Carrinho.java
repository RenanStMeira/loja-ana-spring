package com.analoja.artesanato.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Carrinho")
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idCarrinho;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;
}
