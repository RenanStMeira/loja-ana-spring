package com.analoja.artesanato.entity;

import com.analoja.artesanato.enums.Categoria;
import com.analoja.artesanato.enums.Cor;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "Avaliacao")
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idAvaliacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "PONTUACAO")
    private Integer pontuacao;

    @Column(name = "TEXTO")
    private String texto;

    @Column(name = "DATA")
    private Date data;
}
