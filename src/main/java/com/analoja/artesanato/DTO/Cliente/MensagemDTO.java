package com.analoja.artesanato.DTO.Cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MensagemDTO {

    @Schema(example = "Cadastro realizado com sucesso")
    private String mensagem;

    @Schema(example = "1")
    private Integer id;

    public MensagemDTO(String mensagem){
        this.mensagem = mensagem;
    }
}
