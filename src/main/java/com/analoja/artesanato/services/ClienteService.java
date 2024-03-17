package com.analoja.artesanato.services;

import com.analoja.artesanato.DTO.ClienteCreateDTO;
import com.analoja.artesanato.DTO.EnderecoCreateDTO;
import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.entity.Endereco;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.repository.ClienteRepository;
import com.analoja.artesanato.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final EnderecoRepository enderecoRepository;
    private final ObjectMapper objectMapper;

    public Optional<Cliente> findByLogin(String username) {
        return clienteRepository.findByLogin(username);
    }

    @Transactional
    public Cliente cadastrarCliente(ClienteCreateDTO clienteDTO) throws RegraDeNegocioException {
        Cliente cliente = new Cliente();
        Endereco endereco = new Endereco();

        cliente.setNome(clienteDTO.getNome());
        cliente.setSobrenome(clienteDTO.getSobrenome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setCelular(clienteDTO.getCelular());
        cliente.setDataNascimento(clienteDTO.getDataNascimento());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setSenha(passwordEncoder.encode(clienteDTO.getSenha()));

        Cliente savedCliente = clienteRepository.save(cliente);

        endereco.setRua(clienteDTO.getEndereco().getRua());
        endereco.setNumero(clienteDTO.getEndereco().getNumero());
        endereco.setCidade(clienteDTO.getEndereco().getCidade());
        endereco.setEstado(clienteDTO.getEndereco().getEstado());
        endereco.setCep(clienteDTO.getEndereco().getCep());

        endereco.setCliente(savedCliente);

        Endereco savedEndereco = enderecoRepository.save(endereco);

        savedCliente.setEndereco(savedEndereco);
        return clienteRepository.save(savedCliente);
    }

    public Cliente atualizarCliente(Integer idCliente, ClienteCreateDTO clienteDTO) throws RegraDeNegocioException {
        Cliente clienteRecuperado = findById(idCliente);

        clienteRecuperado.setNome(clienteDTO.getNome());
        clienteRecuperado.setSobrenome(clienteDTO.getSobrenome());
        clienteRecuperado.setCpf(clienteDTO.getCpf());
        clienteRecuperado.setCelular(clienteDTO.getCelular());
        clienteRecuperado.setDataNascimento(clienteDTO.getDataNascimento());
        clienteRecuperado.setEmail(clienteDTO.getEmail());
        clienteRecuperado.setSenha(passwordEncoder.encode(clienteDTO.getSenha()));

        Endereco enderecoRecuperado = clienteRecuperado.getEndereco();
        enderecoRecuperado.setRua(clienteDTO.getEndereco().getRua());
        enderecoRecuperado.setNumero(clienteDTO.getEndereco().getNumero());
        enderecoRecuperado.setCidade(clienteDTO.getEndereco().getCidade());
        enderecoRecuperado.setEstado(clienteDTO.getEndereco().getEstado());
        enderecoRecuperado.setCep(clienteDTO.getEndereco().getCep());

        Endereco savedEndereco = enderecoRepository.save(enderecoRecuperado);

        clienteRecuperado.setEndereco(savedEndereco);

        return clienteRepository.save(clienteRecuperado);
    }

    public Page<Cliente> findAll(Pageable pageable){
        Pageable pageableOrdenadoPorId = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("idCliente"));
        return clienteRepository.findAll(pageableOrdenadoPorId);
    }

    public Cliente findById(Integer idCliente) throws RegraDeNegocioException {
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RegraDeNegocioException("Cliente não encontrado"));
    }

}
