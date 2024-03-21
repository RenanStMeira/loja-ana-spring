package com.analoja.artesanato.DTO.Carrinho;

import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.entity.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class CarrinhoCreateDTO {

    @NotNull
    private Cliente cliente;

    @NotNull
    private Produto produto;

    @NotNull
    @Schema(description = "Quantidade de produtos", example = "1")
    private Integer quantidade;
}
