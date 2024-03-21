package com.analoja.artesanato.DTO.Pedido;

import com.analoja.artesanato.entity.Cliente;
import com.analoja.artesanato.entity.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class PedidoCreateDTO {

    @NotNull
    @Schema(description = "Data do pedido", example = "2021-10-10")
    private Date data;

    @NotNull
    private Integer idCliente;

    @NotNull
    private Integer idProduto;

    @NotNull
    @Schema(description = "Quantidade do produto", example = "2")
    private Integer quantidade;

    @NotNull
    @Schema(description = "Status do pedido", example = "Em andamento")
    private String status;

    public Integer getCliente() {
        return idCliente;
    }
}
