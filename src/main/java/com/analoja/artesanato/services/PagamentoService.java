package com.analoja.artesanato.services;

import com.analoja.artesanato.DTO.Pagamento.PagamentoCreateDTO;
import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.entity.Pagamento;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.repository.ClienteRepository;
import com.analoja.artesanato.repository.PagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final ClienteRepository clienteRepository;

    public Pagamento criarPagamento (PagamentoCreateDTO pagamentoDTO) throws RegraDeNegocioException {
        Pagamento pagamento = new Pagamento();
        Cliente cliente = clienteRepository.findById(pagamentoDTO.getCliente())
                .orElseThrow(() -> new RegraDeNegocioException("Cliente não encontrado"));
        pagamento.setCliente(cliente);
        pagamento.setNumero_cartao(pagamentoDTO.getNumero_cartao());
        pagamento.setData_validade(pagamentoDTO.getData_validade());
        pagamento.setCvv(pagamentoDTO.getCvv());

        return pagamentoRepository.save(pagamento);
    }

    public Pagamento atualizarPagamento(Integer id, PagamentoCreateDTO pagamentoDTO) throws RegraDeNegocioException {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Pagamento não encontrado"));
        Cliente cliente = clienteRepository.findById(pagamentoDTO.getCliente())
                .orElseThrow(() -> new RegraDeNegocioException("Cliente não encontrado"));
        pagamento.setCliente(cliente);
        pagamento.setNumero_cartao(pagamentoDTO.getNumero_cartao());
        pagamento.setData_validade(pagamentoDTO.getData_validade());
        pagamento.setCvv(pagamentoDTO.getCvv());

        return pagamentoRepository.save(pagamento);
    }

    public List<Pagamento> buscarTodosPagamentos() throws RegraDeNegocioException {
        return pagamentoRepository.findAll();
    }

    public Pagamento buscarPagamentoPorId(Integer id) throws RegraDeNegocioException {
        return pagamentoRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Pagamento não encontrado"));
    }

    public void deletarPagamento(Integer id) throws RegraDeNegocioException {
        pagamentoRepository.deleteById(id);
    }
}
