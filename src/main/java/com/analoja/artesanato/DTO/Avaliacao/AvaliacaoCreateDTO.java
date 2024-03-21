package com.analoja.artesanato.DTO.Avaliacao;

import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.entity.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class AvaliacaoCreateDTO {

    @NotNull
    private Cliente cliente;

    @NotNull
    private Produto produto;

    @NotNull
    @Schema(description = "Pontuação da avaliação", example = "5")
    private Integer pontuacao;

    @NotNull
    @Schema(description = "Texto da avaliação", example = "Produto muito bom, recomendo!")
    private String texto;

    @NotNull
    @Schema(description = "Data da avaliação", example = "2021-10-10")
    private Date data;
}
