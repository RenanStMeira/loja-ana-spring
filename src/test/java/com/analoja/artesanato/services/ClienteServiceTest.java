package com.analoja.artesanato.services;

import com.analoja.artesanato.DTO.Cliente.ClienteCreateDTO;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.postgresql.hostchooser.HostRequirement.any;

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

    @DisplayName("Teste para buscar um cliente por ID")
    @Test
    void testBuscarClientePorId() throws RegraDeNegocioException {
        Cliente cliente = MockCliente.retornaClienteEntity();
        cliente.setIdCliente(1);

        when(clienteRepository.findById(anyInt())).thenReturn(Optional.of(cliente));

        Cliente clienteRecuperado = clienteService.buscarPorId(1);

        assertEquals(cliente, clienteRecuperado);
    }

    @DisplayName("Testar buscar cliente por cpf")
    @Test
    void testaBuscarClientePorCpf() throws RegraDeNegocioException {
        Cliente cliente = MockCliente.retornaClienteEntity();
        cliente.setCpf("12345678901");

        when(clienteRepository.findByCpf(any(String.class))).thenReturn(Optional.of(cliente));

        Cliente clienteRecuperado = clienteService.buscarClientePorCpf("12345678901");

        assertEquals(cliente, clienteRecuperado);
    }

    @DisplayName("Testar buscar cliente por email")
    @Test
    void testBuscarClientePorEmail() throws RegraDeNegocioException {
        Cliente cliente = MockCliente.retornaClienteEntity();
        cliente.setEmail("test@email.com");

        when(clienteRepository.findByEmail(any(String.class))).thenReturn(Optional.of(cliente));

        Cliente clienteRecuperado = clienteService.buscarClientePorEmail("test@email.com");

        assertEquals(cliente, clienteRecuperado);

    }

    @DisplayName("Testar buscar todos os cliente")
    @Test
    void testBuscarTodosClientes() throws RegraDeNegocioException {
        Pageable pageable = PageRequest.of(0,10);

        List<Cliente> clientes = new ArrayList<>();

        for(int i =0; i < 10; i++) {
            Cliente c = new Cliente();
            c.setIdCliente(i);
            clientes.add(c);
        }

        Page<Cliente> clientePage = new PageImpl<>(clientes);

        when(clienteRepository.findAll(any(Pageable.class))).thenReturn(clientePage);

        Page<Cliente> result = clienteService.buscarTodosClientes(pageable);

        assertEquals(clientePage, result);
    }

    @DisplayName("Testar deletar cliente")
    @Test
    void testDeletarUsuario() throws RegraDeNegocioException {
        Cliente cliente = MockCliente.retornaClienteEntity();
        Integer idCliente = 1;
        cliente.setIdCliente(1);

        when(clienteRepository.findById(idCliente)).thenReturn(Optional.of(cliente));

        clienteService.deletarCliente(idCliente);

        assertEquals(cliente, cliente);
    }

    @DisplayName("Testar buscar cliente por login")
    @Test
    void testBuscarClientePorLogin() {
        Cliente cliente = MockCliente.retornaClienteEntity();

        when(clienteRepository.findByEmail(any(String.class))).thenReturn(Optional.of(cliente));

        Optional<Cliente> clienteRecuperado = clienteService.findByLogin("test");

        assertEquals(cliente, clienteRecuperado.get());
    }
}