package com.analoja.artesanato.DTO.Endereco;

import com.analoja.artesanato.entity.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Data
public class EnderecoCreateDTO {

    @NotNull
    private Integer idCliente;

    @NotNull
    @Schema(description = "Rua do endereço", example = "Rua das Flores")
    private String rua;

    @NotNull
    @Schema(description = "Número do endereço", example = "123")
    private Integer numero;

    @NotNull
    @Schema(description = "Bairro do endereço", example = "Centro")
    private String cidade;

    @NotNull
    @Schema(description = "Cidade do endereço", example = "São Paulo")
    private String estado;

    @NotNull
    @Schema(description = "CEP do endereço", example = "12345-678")
    private String cep;

}
