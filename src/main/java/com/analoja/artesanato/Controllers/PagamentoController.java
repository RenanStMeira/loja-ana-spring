package com.analoja.artesanato.Controllers;

import com.analoja.artesanato.DTO.Pagamento.PagamentoCreateDTO;
import com.analoja.artesanato.entity.Pagamento;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.services.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/pagamento")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @GetMapping
    public ResponseEntity<?> buscarTodosPagamentos() throws RegraDeNegocioException {
        return new ResponseEntity<>(pagamentoService.buscarTodosPagamentos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPagamentoPorId(@PathVariable Integer id) throws RegraDeNegocioException {
        Pagamento pagamento = pagamentoService.buscarPagamentoPorId(id);
        return new ResponseEntity<>(pagamento, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizarPagamento(@PathVariable Integer id, @RequestBody PagamentoCreateDTO pagamentoCreateDTO) throws RegraDeNegocioException {
        Pagamento pagamento = pagamentoService.atualizarPagamento(id, pagamentoCreateDTO);
        return new ResponseEntity<>(pagamento, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pagamento> criarPagamento(@RequestBody PagamentoCreateDTO pagamentoCreateDTO) throws RegraDeNegocioException {
        Pagamento pagamento = pagamentoService.criarPagamento(pagamentoCreateDTO);
        return new ResponseEntity<>(pagamento, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPagamento(@PathVariable Integer id) throws RegraDeNegocioException {
        pagamentoService.deletarPagamento(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
