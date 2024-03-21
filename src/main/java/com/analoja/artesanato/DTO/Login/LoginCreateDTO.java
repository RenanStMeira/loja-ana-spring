package com.analoja.artesanato.DTO.Login;

import com.analoja.artesanato.entity.Admin;
import com.analoja.artesanato.entity.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class LoginCreateDTO {

    @NotNull
    @Schema(description = "Email do login", example = "login@email.com", required = true)
    private String email;

    @NotNull
    @Schema(description = "Senha do login", example = "senha123", required = true)
    private String senha;


    private Cliente cliente;
    private Admin admin;
}
