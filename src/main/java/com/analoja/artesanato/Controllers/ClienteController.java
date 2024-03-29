package com.analoja.artesanato.Controllers;

import com.analoja.artesanato.Controllers.interfaces.CarrinhoControllerInterface;
import com.analoja.artesanato.Controllers.interfaces.ClienteControllerInterface;
import com.analoja.artesanato.DTO.Cliente.ClienteCreateDTO;
import com.analoja.artesanato.DTO.Cliente.MensagemDTO;
import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.services.ClienteService;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Cliente", description = "Endpoint de Cliente")
@RequestMapping("/cliente")
public class ClienteController implements ClienteControllerInterface {

    private final ClienteService clienteService;

    @GetMapping
    public ResponseEntity<Page<Cliente>> buscarTodosClientes(Pageable pageable) throws RegraDeNegocioException {
        Page<Cliente> clientes = clienteService.buscarTodosClientes(pageable);
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{idCliente}")
    public ResponseEntity<Cliente> byscarPorId(@PathVariable("idCliente") Integer idCliente) throws RegraDeNegocioException {
        Cliente cliente = clienteService.buscarPorId(idCliente);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/buscarPorEmail/{email}")
    public ResponseEntity<Cliente> buscarPorEmail(@PathVariable("email") String email) throws RegraDeNegocioException {
        Cliente cliente = clienteService.buscarClientePorEmail(email);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @GetMapping("/buscarPorCpf/{cpf}")
    public ResponseEntity<Cliente> buscarPorCpf(@PathVariable("cpf") String cpf) throws RegraDeNegocioException {
        Cliente cliente = clienteService.buscarClientePorCpf(cpf);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<MensagemDTO> cadatrarClienteResponseEntity(@RequestBody ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {
        MensagemDTO cliente = clienteService.cadastrarCliente(clienteCreateDTO);
        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{idCliente}")
    public ResponseEntity<MensagemDTO> atualizarClienteResponseEntity(@PathVariable Integer idCliente, @RequestBody ClienteCreateDTO clienteCreateDTO) throws RegraDeNegocioException {
        MensagemDTO cliente = clienteService.atualizarCliente(idCliente, clienteCreateDTO);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{idCliente}")
    public ResponseEntity<Void> deletarClienteResponseEntity(@PathVariable Integer idCliente) throws RegraDeNegocioException {
        clienteService.deletarCliente(idCliente);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
