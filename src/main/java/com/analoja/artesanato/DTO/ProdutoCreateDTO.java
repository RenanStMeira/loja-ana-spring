package com.analoja.artesanato.DTO;

import com.analoja.artesanato.entity.Produto;
import com.analoja.artesanato.enums.Categoria;
import com.analoja.artesanato.enums.Cor;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
public class ProdutoCreateDTO {

    @NotNull
    @Schema(description = "Nome do produto", example = "Vaso de cerâmica")
    private String nome;

    @NotNull
    @Schema(description = "Descrição do produto", example = "Vaso de cerâmica feito a mão")
    private String descricao;

    @NotNull
    @Schema(description = "Preço do produto", example = "100.00")
    private BigDecimal preco;

}
