package com.analoja.artesanato.Controllers;

import com.analoja.artesanato.entity.Avaliacao;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.services.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;

    @GetMapping
    public ResponseEntity<Page<Avaliacao>> listarTodasAvaliacoes(Pageable pageable) throws RegraDeNegocioException {
        Page<Avaliacao> avaliacoes = avaliacaoService.listarTodasAvaliacoes(pageable);
        return new ResponseEntity<>(avaliacoes, HttpStatus.OK);
    }

    @GetMapping("/{idAvaliacao}")
    public ResponseEntity<Avaliacao> buscarAvaliacaoPorId(Integer idAvaliacao) throws RegraDeNegocioException {
        Avaliacao avaliacao = avaliacaoService.buscarAvaliacaoPorId(idAvaliacao);
        return new ResponseEntity<>(avaliacao, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Avaliacao> criarAvaliacao(Avaliacao avaliacao) throws RegraDeNegocioException {
        Avaliacao avaliacaoCriada = avaliacaoService.criarAvaliacao(avaliacao);
        return new ResponseEntity<>(avaliacaoCriada, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Avaliacao> atualizarAvaliacao(Integer idAvaliacao, Avaliacao avaliacao) throws RegraDeNegocioException {
        Avaliacao avaliacaoAtualizada = avaliacaoService.atualizarAvaliacao(idAvaliacao, avaliacao);
        return new ResponseEntity<>(avaliacaoAtualizada, HttpStatus.OK);
    }

    @GetMapping("/deletar/{idAvaliacao}")
    public void deletarAvaliacao(Integer idAvaliacao) throws RegraDeNegocioException {
        avaliacaoService.deletarAvaliacao(idAvaliacao);
    }
}