package com.analoja.artesanato.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Login")
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idLogin;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "SENHA")
    private String senha;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
}
