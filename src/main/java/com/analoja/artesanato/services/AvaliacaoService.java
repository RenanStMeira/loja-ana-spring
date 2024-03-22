package com.analoja.artesanato.services;

import com.analoja.artesanato.DTO.Avaliacao.AvaliacaoCreateDTO;
import com.analoja.artesanato.entity.Avaliacao;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.repository.AvaliacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;

    public Avaliacao criarAvaliacao(AvaliacaoCreateDTO avaliacaoDTO) throws RegraDeNegocioException {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setCliente(avaliacaoDTO.getCliente());
        avaliacao.setProduto(avaliacaoDTO.getProduto());
        avaliacao.setPontuacao(avaliacaoDTO.getPontuacao());
        avaliacao.setTexto(avaliacaoDTO.getTexto());
        avaliacao.setData(avaliacaoDTO.getData());

        return avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao atualizarAvaliacao(Integer idAvaliacao, AvaliacaoCreateDTO avaliacaoDTO) throws RegraDeNegocioException {
        Avaliacao avaliacao = avaliacaoRepository.findById(idAvaliacao)
                .orElseThrow(() -> new RegraDeNegocioException("Avaliação não encontrada"));

        avaliacao.setCliente(avaliacaoDTO.getCliente());
        avaliacao.setProduto(avaliacaoDTO.getProduto());
        avaliacao.setPontuacao(avaliacaoDTO.getPontuacao());
        avaliacao.setTexto(avaliacaoDTO.getTexto());
        avaliacao.setData(avaliacaoDTO.getData());

        return avaliacaoRepository.save(avaliacao);
    }

    public Page<Avaliacao> listarTodasAvaliacoes(Pageable pageable) throws RegraDeNegocioException {
        Pageable page = PageRequest.of(pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by(Sort.Direction.ASC, "idAvaliacao"));

        return avaliacaoRepository.findAll(page);
    }

    public Avaliacao buscarAvaliacaoPorId(Integer idAvaliacao) throws RegraDeNegocioException {

        return avaliacaoRepository.findById(idAvaliacao)
                .orElseThrow(() -> new RegraDeNegocioException("Avaliação não encontrada"));
    }

    public void deletarAvaliacao(Integer idAvaliacao) throws RegraDeNegocioException {
        Avaliacao avaliacao = avaliacaoRepository.findById(idAvaliacao)
                .orElseThrow(() -> new RegraDeNegocioException("Avaliação não encontrada"));

        avaliacaoRepository.delete(avaliacao);
    }
}
