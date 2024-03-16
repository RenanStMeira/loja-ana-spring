package com.analoja.artesanato.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Estoque")
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idEstoque;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @Column(name = "QUANTIDADE_MINIMA")
    private Integer quantidade_minima;
}