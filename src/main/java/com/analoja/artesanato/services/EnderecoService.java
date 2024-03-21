package com.analoja.artesanato.services;

import com.analoja.artesanato.DTO.Endereco.EnderecoCreateDTO;
import com.analoja.artesanato.DTO.Endereco.EnderecoResponseDTO;
import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.entity.Endereco;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.repository.ClienteRepository;
import com.analoja.artesanato.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final ObjectMapper objectMapper;
    private final ClienteRepository clienteRepository;

    public EnderecoResponseDTO criarEndereco(EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        Endereco endereco = new Endereco();

        endereco.setRua(enderecoCreateDTO.getRua());
        endereco.setNumero(enderecoCreateDTO.getNumero());
        endereco.setCidade(enderecoCreateDTO.getCidade());
        endereco.setEstado(enderecoCreateDTO.getEstado());
        endereco.setCep(enderecoCreateDTO.getCep());

        Cliente cliente = clienteRepository.findById(enderecoCreateDTO.getIdCliente())
                .orElseThrow(() -> new RegraDeNegocioException("Cliente não encontrado"));
        endereco.setCliente(cliente);


        Endereco savedEndereco = enderecoRepository.save(endereco);

        return objectMapper.convertValue(savedEndereco, EnderecoResponseDTO.class);
    }

    public EnderecoResponseDTO atualizarEndereco(Integer idEndereco, EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        Endereco endereco = enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));

        endereco.setRua(enderecoCreateDTO.getRua());
        endereco.setNumero(enderecoCreateDTO.getNumero());
        endereco.setCidade(enderecoCreateDTO.getCidade());
        endereco.setEstado(enderecoCreateDTO.getEstado());
        endereco.setCep(enderecoCreateDTO.getCep());

        Cliente cliente = clienteRepository.findById(enderecoCreateDTO.getIdCliente())
                .orElseThrow(() -> new RegraDeNegocioException("Cliente não encontrado"));
        endereco.setCliente(cliente);

        Endereco savedEndereco = enderecoRepository.save(endereco);

        return objectMapper.convertValue(savedEndereco, EnderecoResponseDTO.class);
    }

    public List<Endereco> listarEnderecos() {
        return enderecoRepository.findAll();
    }

    public EnderecoResponseDTO buscarPorId(Integer idEndereco) throws RegraDeNegocioException {
        Endereco endereco = enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));

        return objectMapper.convertValue(endereco, EnderecoResponseDTO.class);
    }

    public void deletarEndereco(Integer idEndereco) throws RegraDeNegocioException {
        Endereco endereco = enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new RegraDeNegocioException("Endereço não encontrado"));

        enderecoRepository.delete(endereco);
    }
}
