package com.analoja.artesanato.DTO.Pagamento;

import com.analoja.artesanato.entity.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PagamentoCreateDTO {

    @NotNull
    private Cliente cliente;

    @NotNull
    @Schema(description = "Número do cartão", example = "1234 5678 1234 5678")
    private String numero_cartao;

    @NotNull
    @Schema(description = "Data de validade do cartão", example = "2023-12-31")
    private Date data_validade;

    @NotNull
    @Schema(description = "CVV do cartão", example = "123")
    private Integer cvv;
}
