package com.analoja.artesanato.Controllers;

import com.analoja.artesanato.Controllers.interfaces.ProdutoControllerInterface;
import com.analoja.artesanato.DTO.Produto.ProdutoCreateDTO;
import com.analoja.artesanato.entity.Produto;
import com.analoja.artesanato.enums.Categoria;
import com.analoja.artesanato.enums.Cor;
import com.analoja.artesanato.exceptions.RegraDeNegocioException;
import com.analoja.artesanato.services.ProdutoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Validated
@RequiredArgsConstructor
@Tag(name = "Produto", description = "Endpoint de Produto")
@RequestMapping("/produto")
public class ProdutoController implements ProdutoControllerInterface {

    private final ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Page<Produto>> findAll(Pageable pageable) {
        Page<Produto> produtos = produtoService.findAll(pageable);
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/cor")
    public ResponseEntity<Page<Produto>> findByCor(@RequestParam("cor") String corStr, Pageable pageable) {
        Cor cor = Cor.valueOf(corStr.toUpperCase());
        Page<Produto> produtos = produtoService.findByCor(cor, pageable);
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @GetMapping("/{idProduto}")
    public ResponseEntity<Produto> findById(@PathVariable("idProduto") Integer idProduto) throws RegraDeNegocioException {
        Produto produto = produtoService.findById(idProduto);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @GetMapping("/categoria")
    public ResponseEntity<Page<Produto>> listByCategoria(@RequestParam("categoria") String categoriaStr, Pageable pageable) throws RegraDeNegocioException {
        Categoria categoria = Categoria.valueOf(categoriaStr.toUpperCase());
        Page<Produto> produtos = produtoService.listByCategoria(categoria, pageable);
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Produto> create(@RequestParam("categoria") String categoriaStr, @RequestParam("cor") String corStr, @RequestBody @Valid ProdutoCreateDTO produtoDTO) throws RegraDeNegocioException {
        Categoria categoria = Categoria.valueOf(categoriaStr.toUpperCase());
        Cor cor = Cor.valueOf(corStr.toUpperCase());
        Produto produto = produtoService.create(produtoDTO, categoria, cor);
        return new ResponseEntity<>(produto, HttpStatus.CREATED);
    }

    @PutMapping("/update/{idProduto}")
    public ResponseEntity<Produto> update(@PathVariable("idProduto") Integer idProduto, @RequestParam("categoria") String categoriaStr, @RequestParam("cor") String corStr, @RequestBody @Valid ProdutoCreateDTO produtoDTO) throws RegraDeNegocioException {
        Categoria categoria = Categoria.valueOf(categoriaStr.toUpperCase());
        Cor cor = Cor.valueOf(corStr.toUpperCase());
        Produto produto = produtoService.update(idProduto, produtoDTO, categoria, cor);
        return new ResponseEntity<>(produto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idProduto}")
    public ResponseEntity<?> delete(@PathVariable("idProduto") Integer idProduto) throws RegraDeNegocioException {
        produtoService.delete(idProduto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
