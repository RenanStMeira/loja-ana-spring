package com.analoja.artesanato.services;

import com.analoja.artesanato.DTO.Cliente.ClienteCreateDTO;
import com.analoja.artesanato.DTO.Cliente.MensagemDTO;
import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.entity.Endereco;
import com.analoja.artesanato.enums.MensagemRetorno;
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

    private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";
    private static final String ID_NAO_ENCONTRADO = "ID não encontrado";
    private static final String CPF_NAO_ENCONTRADO = "CPF não encontrado";
    private static final String EMAIL_NAO_ENCONTRADO = "Email não encontrado";

    @Transactional
    public MensagemDTO cadastrarCliente(ClienteCreateDTO clienteDTO) throws RegraDeNegocioException {
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
        return new MensagemDTO(MensagemRetorno.CADASTRO_COM_SUCESSO.getMensagemRetorno(), savedCliente.getIdCliente());
    }


    public MensagemDTO atualizarCliente(Integer idCliente, ClienteCreateDTO clienteDTO) throws RegraDeNegocioException {
        existsId(idCliente);

        Cliente clienteRecuperado = buscarPorId(idCliente);

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

        return new MensagemDTO(MensagemRetorno.EDITADO_COM_SUCESSO.getMensagemRetorno(), clienteRecuperado.getIdCliente());
    }

    public Cliente buscarClientePorCpf(String cpf) throws RegraDeNegocioException {
        cpfExists(cpf);

        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RegraDeNegocioException(CLIENTE_NAO_ENCONTRADO ));
    }

    public Cliente buscarClientePorEmail(String email) throws RegraDeNegocioException {
        emailExists(email);

        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new RegraDeNegocioException(CLIENTE_NAO_ENCONTRADO ));
    }

    public Page<Cliente> buscarTodosClientes(Pageable pageable) throws RegraDeNegocioException {
        Pageable pageableOrdenadoPorId = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("idCliente"));
        return clienteRepository.findAll(pageableOrdenadoPorId);
    }

    public Cliente buscarPorId(Integer idCliente) throws RegraDeNegocioException {
        existsId(idCliente);

        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RegraDeNegocioException(CLIENTE_NAO_ENCONTRADO ));
    }

    public void deletarCliente(Integer idCliente) throws RegraDeNegocioException {
        existsId(idCliente);
        Cliente cliente = buscarPorId(idCliente);
        clienteRepository.delete(cliente);
    }

    private Cliente existsId(Integer idCliente) throws RegraDeNegocioException {
        return clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RegraDeNegocioException(ID_NAO_ENCONTRADO));
    }

    private Cliente cpfExists(String cpf) throws RegraDeNegocioException {
        return clienteRepository.findByCpf(cpf)
                .orElseThrow(() -> new RegraDeNegocioException(CPF_NAO_ENCONTRADO));
    }

    private Cliente emailExists(String email) throws RegraDeNegocioException {
        return clienteRepository.findByEmail(email)
                .orElseThrow(() -> new RegraDeNegocioException(EMAIL_NAO_ENCONTRADO));
    }

    public Optional<Cliente> findByLogin(String username) {
        return clienteRepository.findByEmail(username);
    }
}
