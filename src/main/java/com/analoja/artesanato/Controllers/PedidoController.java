package com.analoja.artesanato.Controllers;

import com.analoja.artesanato.DTO.Pedido.PedidoCreateDTO;
import com.analoja.artesanato.entity.Pedido;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.services.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedidoPorId(@PathVariable Integer id) throws RegraDeNegocioException {
        Pedido pedido = pedidoService.buscarPedidoPorId(id);
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> buscarTodosPedidos() throws RegraDeNegocioException {
        return new ResponseEntity<>(pedidoService.buscarTodosPedidos(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Integer id, @RequestBody PedidoCreateDTO pedidoDTO) throws RegraDeNegocioException {
        Pedido pedido = pedidoService.atualizarPedido(id, pedidoDTO);
        return new ResponseEntity<>(pedido, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody PedidoCreateDTO pedidoDTO) throws RegraDeNegocioException {
        Pedido pedido = pedidoService.criarPedido(pedidoDTO);
        return new ResponseEntity<>(pedido, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarPedido(@PathVariable Integer id) throws RegraDeNegocioException {
        pedidoService.deletarPedido(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
