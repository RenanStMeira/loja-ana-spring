package com.analoja.artesanato.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class
AdminCreateDTO {

    @NotNull
    @Schema(description = "Nome do administrador", example = "João da Silva", required = true)
    private String nome;

    @NotNull
    @Schema(description = "Email do administrador", example = "admin@rmail.com")
    private String email;

    @NotNull
    @Schema(description = "Senha do administrador", example = "123456", required = true)
    private String senha;
}
