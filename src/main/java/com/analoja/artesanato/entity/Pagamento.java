package com.analoja.artesanato.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(schema = "LojaAna", name = "Pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idPagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "numero_cartao")
    private String numero_cartao;

    @Column(name = "data_validade")
    private Date data_validade;

    @Column(name = "cvv")
    private Integer cvv;
}
