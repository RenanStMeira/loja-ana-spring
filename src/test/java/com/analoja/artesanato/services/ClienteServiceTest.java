package com.analoja.artesanato.services;

import com.analoja.artesanato.DTO.Cliente.ClienteCreateDTO;
import com.analoja.artesanato.DTO.Cliente.ClienteResponseDTO;
import com.analoja.artesanato.DTO.Cliente.MensagemDTO;
import com.analoja.artesanato.MockCliente;
import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.entity.Endereco;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.repository.ClienteRepository;
import com.analoja.artesanato.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        clienteService = new ClienteService(clienteRepository, passwordEncoder, enderecoRepository, objectMapper);
    }

    @DisplayName("Teste para cadastrar um cliente")
    @Test
    void testCadastrarCliente() throws RegraDeNegocioException {
        ClienteCreateDTO clienteDTO = MockCliente.retornaClienteCreateDTO();
        Cliente cliente = MockCliente.retornaClienteEntity();
        Endereco endereco = cliente.getEndereco();

        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
        when(enderecoRepository.save(any(Endereco.class))).thenReturn(endereco);
        when(passwordEncoder.encode(clienteDTO.getSenha())).thenReturn("encodedPassword");

        MensagemDTO result = clienteService.cadastrarCliente(clienteDTO);

        assertEquals(cliente.getIdCliente(), result.getId());
        assertEquals("Cliente cadastrado com sucesso", result.getMensagem());
    }

    @DisplayName("Teste atualizar um cliente")
    @Test
    void testAtualizarCliente() throws RegraDeNegocioException {
        ClienteCreateDTO clienteDTO = MockCliente.retornaClienteCreateDTO();

        Cliente cliente = MockCliente.retornaClienteEntity();

        Cliente clienteExistente = MockCliente.retornaClienteEntity();

        when(clienteRepository.findById(clienteExistente.getIdCliente())).thenReturn(Optional.of(clienteExistente));
        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);

        clienteRepository.save(clienteExistente);

        MensagemDTO clienteAtualizado = clienteService.atualizarCliente(clienteExistente.getIdCliente(), clienteDTO);

        assertEquals(clienteExistente.getIdCliente(), clienteAtualizado.getId());
        assertEquals("Cliente Editado com sucesso", clienteAtualizado.getMensagem());
    }
}