package com.analoja.artesanato.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idEndereco;

    @OneToOne(mappedBy = "endereco")
    private Admin admin;

    @OneToOne(mappedBy = "endereco")
    @JsonBackReference
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Column(name = "RUA")
    private String rua;

    @Column(name = "NUMERO")
    private Integer numero;

    @Column(name = "BAIRRO")
    private String cidade;

    @Column(name = "CIDADE")
    private String estado;

    @Column(name = "CEP")
    private String cep;

}