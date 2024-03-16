package com.analoja.artesanato.entity;


import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "Pedido")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idPedido;

    @Column(name = "DATA")
    private Date data;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "QUANTIDADE")
    private Integer quantidade;

    @Column(name = "PRECO_TOTAL")
    private BigDecimal preco_total;

    @Column(name = "STATUS")
    private String status;
}