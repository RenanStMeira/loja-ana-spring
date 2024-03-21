package com.analoja.artesanato.DTO.Cliente;

import com.analoja.artesanato.entity.Endereco;
import com.analoja.artesanato.entity.Login;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "Cliente")
public class ClienteResponseDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer idCliente;

    @Column(name = "Nome")
    private String nome;

    @Column(name = "Sobrenome")
    private String sobrenome;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "Celular")
    private String celular;

    @Column(name = "DataNascimento")
    private Date dataNascimento;

    @Column(name = "Email")
    private String email;

    @Column(name = "Senha")
    private String senha;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
}
