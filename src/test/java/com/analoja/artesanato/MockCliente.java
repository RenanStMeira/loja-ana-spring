package com.analoja.artesanato;

import com.analoja.artesanato.DTO.Cliente.ClienteCreateDTO;
import com.analoja.artesanato.DTO.Endereco.EnderecoCreateDTO;
import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.entity.Endereco;

import java.util.Date;
import java.util.Random;

public class MockCliente {

    public static Cliente retornaClienteEntity() {
        Cliente cliente = new Cliente();
        cliente.setIdCliente(new Random().nextInt());
        cliente.setNome("Nome");
        cliente.setSobrenome("Sobrenome");
        cliente.setCpf("12345678901");
        cliente.setCelular("11999999999");
        cliente.setDataNascimento(new Date());
        cliente.setEmail("teste@email.com");
        cliente.setSenha("senha123");

        Endereco endereco = new Endereco();
        endereco.setRua("Rua");
        endereco.setNumero(123);
        endereco.setCidade("Cidade");
        endereco.setEstado("Estado");
        endereco.setCep("12345678");
        endereco.setCliente(cliente);

        cliente.setEndereco(endereco);

        return cliente;
    }

    public static ClienteCreateDTO retornaClienteCreateDTO() {
        ClienteCreateDTO clienteCreateDTO = new ClienteCreateDTO();
        clienteCreateDTO.setNome("Nome");
        clienteCreateDTO.setSobrenome("Sobrenome");
        clienteCreateDTO.setCpf("12345678901");
        clienteCreateDTO.setCelular("11999999999");
        clienteCreateDTO.setDataNascimento(new Date());
        clienteCreateDTO.setEmail("teste@email.com");
        clienteCreateDTO.setSenha("senha123");

        EnderecoCreateDTO endereco = new EnderecoCreateDTO(1, "Rua", 123, "Cidade", "Estado", "12345678");

        clienteCreateDTO.setEndereco(endereco);

        return clienteCreateDTO;
    }
}