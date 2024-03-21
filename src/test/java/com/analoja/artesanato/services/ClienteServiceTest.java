//package com.analoja.artesanato.services;
//
//import com.analoja.artesanato.DTO.Cliente.ClienteCreateDTO;
//import com.analoja.artesanato.DTO.Cliente.ClienteResponseDTO;
//import com.analoja.artesanato.MockCliente;
//import com.analoja.artesanato.entity.Cliente;
//import com.analoja.artesanato.entity.Endereco;
//import com.analoja.artesanato.exceptions.RegraDeNegocioException;
//import com.analoja.artesanato.repository.ClienteRepository;
//import com.analoja.artesanato.repository.EnderecoRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//class ClienteServiceTest {
//
//    @InjectMocks
//    private ClienteService clienteService;
//
//    @Mock
//    private ClienteRepository clienteRepository;
//
//    @Mock
//    private PasswordEncoder passwordEncoder;
//
//    @Mock
//    private EnderecoRepository enderecoRepository;
//
//    @BeforeEach
//    void setUp() {
//        clienteService = new ClienteService(clienteRepository, passwordEncoder, enderecoRepository);
//    }
//
//    @DisplayName("Teste para cadastrar um cliente")
//    @Test
//    void testarCadastrarCliente() throws RegraDeNegocioException {
//        ClienteCreateDTO clienteCreateDTO = MockCliente.retornaClienteCreateDTO();
//
//        ClienteResponseDTO cliente = MockCliente.retornaPessoaEntity();
//
//        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
//        when(enderecoRepository.save(any(Endereco.class))).thenReturn(cliente.getEndereco());
//
//        ClienteResponseDTO clienteCadastrado = clienteService.cadastrarCliente(clienteCreateDTO);
//
//        assertEquals(cliente, clienteCadastrado);
//        assertEquals(cliente.getEndereco(), clienteCadastrado.getEndereco());
//    }
//
//    @DisplayName("Teste para atualizar um cliente")
//    @Test
//    void testarAtualizarCliente() throws RegraDeNegocioException {
//        Cliente cliente = MockCliente.retornaPessoaEntity();
//        ClienteCreateDTO clienteCreateDTO = MockCliente.retornaClienteCreateDTO();
//
//        when(clienteRepository.findById(any(Integer.class))).thenReturn(Optional.of(cliente));
//        when(clienteRepository.save(any(Cliente.class))).thenReturn(cliente);
//        when(enderecoRepository.save(any(Endereco.class))).thenReturn(cliente.getEndereco());
//
//        Cliente clienteCadastrado = clienteService.atualizarCliente(cliente.getIdCliente(), clienteCreateDTO);
//
//        assertEquals(cliente, clienteCadastrado);
//        assertEquals(cliente.getEndereco(), clienteCadastrado.getEndereco());
//    }
//}