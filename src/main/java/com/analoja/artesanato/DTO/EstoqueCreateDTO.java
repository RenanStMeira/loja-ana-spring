package com.analoja.artesanato.DTO;

import com.analoja.artesanato.entity.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EstoqueCreateDTO {

    @NotNull
    private Produto produto;

    @NotNull
    @Schema(description = "Quantidade", example = "3")
    private Integer quantidade;

    @NotNull
    @Schema(description = "Quantidade mínima", example = "1")
    private Integer quantidade_minima;
}
