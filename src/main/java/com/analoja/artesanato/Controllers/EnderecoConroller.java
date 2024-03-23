package com.analoja.artesanato.Controllers;

import com.analoja.artesanato.Controllers.interfaces.EnderecoControllerInterface;
import com.analoja.artesanato.DTO.Endereco.EnderecoCreateDTO;
import com.analoja.artesanato.DTO.Endereco.EnderecoResponseDTO;
import com.analoja.artesanato.entity.Endereco;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.services.EnderecoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@Tag(name = "Endereco", description = "Endpoint de Endereco")
@RequestMapping("/endereco")
public class EnderecoConroller implements EnderecoControllerInterface {

    private final EnderecoService enderecoService;

    @GetMapping
    public ResponseEntity<List<Endereco>> listarEnderecos() throws RegraDeNegocioException {
        List<Endereco> enderecos = enderecoService.listarEnderecos();
        return new ResponseEntity<>(enderecos, HttpStatus.OK);
    }

    @GetMapping("/{idEndereco}")
    public ResponseEntity<EnderecoResponseDTO> buscarPorId(@PathVariable Integer idEndereco) throws RegraDeNegocioException {
        EnderecoResponseDTO endereco = enderecoService.buscarPorId(idEndereco);
        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<EnderecoResponseDTO> criarEndereco(@RequestBody EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        EnderecoResponseDTO endereco = enderecoService.criarEndereco(enderecoCreateDTO);
        return new ResponseEntity<>(endereco, HttpStatus.CREATED);
    }

    @PutMapping("/update/{idEndereco}")
    public ResponseEntity<EnderecoResponseDTO> atualizarEndereco(@PathVariable Integer idEndereco, @RequestBody EnderecoCreateDTO enderecoCreateDTO) throws RegraDeNegocioException {
        EnderecoResponseDTO endereco = enderecoService.atualizarEndereco(idEndereco, enderecoCreateDTO);
        return new ResponseEntity<>(endereco, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idEndereco}")
    public ResponseEntity<Void> deletarEndereco(@PathVariable Integer idEndereco) throws RegraDeNegocioException {
        enderecoService.deletarEndereco(idEndereco);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
